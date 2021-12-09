package net.letapp.documenter.services;

import net.letapp.documenter.domaine.AppRoleVo;
import net.letapp.documenter.domaine.AppUserVo;
import net.letapp.documenter.domaine.PasswordRequest;
import net.letapp.documenter.entities.AppUser;
import net.letapp.documenter.entities.PasswordToken;

public interface UserService {

	public AppUser getUser(String email);
	public AppUserVo addUser(AppUserVo vo, String ln);
	public AppUserVo editUser(String email,AppUserVo vo);
	public void removeUser(String email);
	 public AppRoleVo getRoleByRoleName(String string);
	public AppUserVo getVoUser(String username);
	public void editPassword(String username, PasswordRequest vo);
	public String confirmeEditPassword(String tokenId);
	public void resetPassword(String username, PasswordRequest vo);
	public PasswordToken confirmResetPassword(String tokenId);
	public String doResetPassword(PasswordRequest passwordRequest);
	
}
