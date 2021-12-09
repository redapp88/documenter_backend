package net.letapp.documenter.services;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import net.letapp.documenter.dao.AppRoleRepository;
import net.letapp.documenter.dao.AppUserRepository;
import net.letapp.documenter.dao.PasswordTokenRepository;
import net.letapp.documenter.domaine.AppRoleVo;
import net.letapp.documenter.domaine.AppUserVo;
import net.letapp.documenter.domaine.PasswordRequest;
import net.letapp.documenter.domaine.converters.AppRoleConverter;
import net.letapp.documenter.domaine.converters.AppUserConverter;
import net.letapp.documenter.domaine.converters.CategoryConverter;
import net.letapp.documenter.entities.AppUser;
import net.letapp.documenter.entities.PasswordToken;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppRoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;
	@Autowired
	private MailingService mailingService;
	@Autowired
	private Environment environment;

	@Override
	public AppUser getUser(String email) {
		Optional<AppUser> userOpt = this.appUserRepository.findById(email);
		if (!userOpt.isPresent())
			return null;
		else
			return userOpt.get();
	}

	@Override
	public AppUserVo addUser(AppUserVo vo, String ln) {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("lang/res");
		AppUser bo = AppUserConverter.toBo(vo);
		if (this.getUser(bo.getUsername()) != null)
			throw new RuntimeException(this.environment.getProperty("errors.users.error1"));

		String Mailbody = getTrans(ms, ln, "subscribe.hi") + " " + vo.getName() + " "
				+ getTrans(ms, ln, "subscribe.thank_registring") + ".";
		String title = getTrans(ms, ln, "subscribe.Your_Documenter_Account");
		try {
			this.mailingService.sendEmail(Mailbody, title, vo.getUsername(), "LetApp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AppUserConverter.toVo(appUserRepository.save(bo));
	}

	@Override
	public AppUserVo editUser(String email, AppUserVo vo) {
		AppUser bo = this.getUser(email);
		bo.setName(vo.getName());
		bo.setContry(vo.getContry());
		// bo.setState(vo.getState());
		bo.setCity(vo.getCity());
		bo.setDescription(vo.getDescription());
		bo.setCategory(CategoryConverter.toBo(vo.getCategory()));
		bo.setAdress(vo.getAdress());
		bo.setName(vo.getName());

		return AppUserConverter.toVo(appUserRepository.save(bo));
	}

	@Override
	public void removeUser(String email) {
		AppUser bo = this.getUser(email);
		bo.setState("DELETED");
		appUserRepository.save(bo);
	}

	@Override
	public AppRoleVo getRoleByRoleName(String roleName) {
		return AppRoleConverter.toVo(appRoleRepository.findByRoleName(roleName));
	}

	@Override
	public AppUserVo getVoUser(String username) {
		return AppUserConverter.toVo(this.getUser(username));
	}

	@Override
	public void editPassword(String username, PasswordRequest vo) {
		AppUser user = this.getUser(username);
		if (user == null) {
			throw new RuntimeException(this.environment.getProperty("errors.users.error2"));
		}
		if (!this.bCryptPasswordEncoder.matches(vo.getCurrentPassword(), user.getPassword())) {
			throw new RuntimeException(this.environment.getProperty("errors.users.error3"));
		}
		PasswordToken token = new PasswordToken();
		token.setCreationDate(new Date());
		token.setExpDate(new Date(token.getCreationDate().getTime() + (1000 * 60 * 60 * 2 * 5)));
		token.setUser(user);
		token.setType("EDIT");
		token.setLn(vo.getLn());
		token.setNewPassword(this.bCryptPasswordEncoder.encode(vo.getPassword()));
		this.passwordTokenRepository.save(token);
		this.sendPasswordEditEmail(token);

	}

	@Override
	public String confirmeEditPassword(String tokenId) {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("lang/res");
		Optional<PasswordToken> optTk = this.passwordTokenRepository.findById(tokenId);
		if (!optTk.isPresent())
			return getTrans(ms, "en", "confirmeEditPass.request_not_found");
		PasswordToken tk = optTk.get();

		if (!tk.getType().equals("EDIT")) {
			return getTrans(ms, tk.getLn(), "confirmeEditPass.wrong_action");
		}

		if (new Date().after(tk.getExpDate())) {
			return getTrans(ms, tk.getLn(), "confirmeEditPass.deadlines_exceeded");
		} else {

			AppUser user = tk.getUser();
			user.setPassword(tk.getNewPassword());
			this.appUserRepository.save(user);
			this.passwordTokenRepository.deleteById(tk.getId());

			return getTrans(ms, tk.getLn(), "confirmeEditPass.success");
		}
	}

	@Override
	public void resetPassword(String username, PasswordRequest vo) {

		AppUser user = this.getUser(username);
		if (user == null) {
			throw new RuntimeException(this.environment.getProperty("errors.users.error2"));
		}
		PasswordToken token = new PasswordToken();
		token.setCreationDate(new Date());
		token.setExpDate(new Date(token.getCreationDate().getTime() + (1000 * 60 * 60 * 2 * 5)));
		token.setUser(user);
		token.setType("RESET");
		token.setLn(vo.getLn());
		this.passwordTokenRepository.save(token);
		this.sendPasswordResetEmail(token);
	}

	private void sendPasswordEditEmail(PasswordToken token) {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("lang/res");
		try {
			String link = environment.getProperty("my.backend") + "/token/confirmeEditPassword?tokenId="
					+ token.getId();
			String body = "<p><strong>" + getTrans(ms, token.getLn(), "sendPasswordEditEmail.line1") + "&nbsp;<a href='"
					+ link + "'>" + getTrans(ms, token.getLn(), "sendPasswordEditEmail.line2") + "</a></strong></p>"
					+ "<p>" + getTrans(ms, token.getLn(), "sendPasswordEditEmail.line3") + "</p>" + "<p>" + link
					+ "</p>" + "<p>&nbsp;</p>" + "<p><span style='color: #ff0000;'>"
					+ getTrans(ms, token.getLn(), "sendPasswordEditEmail.line4") + "</span></p>";
			String title =getTrans(ms, token.getLn(), "sendPasswordEditEmail.title");
					this.mailingService.sendEmail(body, title,
					token.getUser().getUsername(), "Documenter");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendPasswordResetEmail(PasswordToken token) {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("lang/res");
		try {
			String link = environment.getProperty("my.backend") + "/token/confirmeResetPassword?tokenId="
					+ token.getId();
			String body = "<p><strong>" + getTrans(ms, token.getLn(), "sendPasswordResetEmail.line1") + "&nbsp;<a href='"
					+ link + "'>" + getTrans(ms, token.getLn(), "sendPasswordResetEmail.line2") + "</a></strong></p>"
					+ "<p>" + getTrans(ms, token.getLn(), "sendPasswordResetEmail.line3") + "</p>" + "<p>" + link
					+ "</p>" + "<p>&nbsp;</p>" + "<p><span style='color: #ff0000;'>"
					+ getTrans(ms, token.getLn(), "sendPasswordResetEmail.line4") + "</span></p>";
			String title =getTrans(ms, token.getLn(), "sendPasswordResetEmail.title");

			this.mailingService.sendEmail(body, title, token.getUser().getUsername(),
					"Documenter");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PasswordToken confirmResetPassword(String tokenId) {
		Optional<PasswordToken> optTk = this.passwordTokenRepository.findById(tokenId);
		if (!optTk.isPresent())
			return null;
		PasswordToken tk = optTk.get();

		if (!tk.getType().equals("RESET")) {
			return null;
		}

		if (new Date().after(tk.getExpDate())) {
			return null;
		} else {

			AppUser user = tk.getUser();

			return tk;
		}
	}

	@Override
	public String doResetPassword(PasswordRequest passwordRequest) {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("lang/res");
		Optional<PasswordToken> optTk = this.passwordTokenRepository.findById(passwordRequest.getTokenId());
		if (!optTk.isPresent())
			return getTrans(ms, "en", "resetPass.request_not_found");
		PasswordToken tk = optTk.get();

		if (!tk.getType().equals("RESET")) {
			return getTrans(ms, tk.getLn(), "resetPass.wrong_action");
		}

		if (new Date().after(tk.getExpDate())) {
			return getTrans(ms, tk.getLn(), "resetPass.deadlines_exceeded");
		} else {

			AppUser user = tk.getUser();
			user.setPassword(this.bCryptPasswordEncoder.encode(passwordRequest.getPassword()));
			this.appUserRepository.save(user);
			this.passwordTokenRepository.deleteById(tk.getId());

			return getTrans(ms, tk.getLn(), "resetPass.success");
		}
	}

	private String getTrans(ResourceBundleMessageSource messageSource, String ln, String text) {
		return messageSource.getMessage(text, null, Locale.forLanguageTag(ln));
	}
}
