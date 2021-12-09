package net.letapp.documenter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.letapp.documenter.domaine.PasswordRequest;
import net.letapp.documenter.entities.PasswordToken;
import net.letapp.documenter.services.UserService;

@Controller
@RequestMapping("/token")
public class TokenController {
	private static final String String = null;
	@Autowired
	private UserService userService;

	@GetMapping("/confirmeEditPassword")
	public String confirmeEditPassword(Model model,@RequestParam String tokenId) {
		
		String resp=this.userService.confirmeEditPassword(tokenId);
		model.addAttribute("response",resp) ;
	
		return "passwordEditTemplate";
		
	}
	
	
	@GetMapping("/confirmeResetPassword")
	public String confirmeResetPasswordGet(Model model,@RequestParam String tokenId) {
		PasswordRequest passwordRequest = new PasswordRequest();
		passwordRequest.setTokenId(tokenId);
		PasswordToken resp = this.userService.confirmResetPassword(tokenId);
		if(resp == null) {
			model.addAttribute("response","The request is depreceted or not valid try again") ;
			
			return "GeneralMessage";
		}
		model.addAttribute("passwordRequest",passwordRequest);
		return "passwordResetTemplate";
		
	}
	
	@PostMapping("/confirmeResetPassword")
	public String confirmeResetPasswordPost(Model model,@ModelAttribute("passwordRequest") PasswordRequest passwordRequest) {
		if(passwordRequest.getPassword().equals("") | passwordRequest.getRepassword().equals("") | passwordRequest.getRepassword().length()<7) {
			model.addAttribute("passwordRequest",passwordRequest);
			model.addAttribute("errorMessage","Password should be 6 caracters min");
			return "passwordResetTemplate";
		}
		
		if(!passwordRequest.getPassword().equals(passwordRequest.getRepassword())) {
			model.addAttribute("passwordRequest",passwordRequest);
			model.addAttribute("errorMessage","Password confirmation does not match");
			return "passwordResetTemplate";
		}
			
		String resp=this.userService.doResetPassword(passwordRequest);
		model.addAttribute("response",resp) ;
		return "GeneralMessage";
		
	}
}
