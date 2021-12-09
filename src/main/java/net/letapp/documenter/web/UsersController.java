package net.letapp.documenter.web;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.letapp.documenter.domaine.AppRoleVo;
import net.letapp.documenter.domaine.AppUserVo;
import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.domaine.PasswordRequest;
import net.letapp.documenter.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@PostMapping("/subscribe")
	public void addUser(@RequestBody AppUserVo vo,@RequestParam String ln) {
		System.out.println("Language" + ln);
		vo.setSubDate(new Date());
		AppRoleVo roleVo = this.userService.getRoleByRoleName("USER");
		vo.setAppRole(roleVo);
		vo.setState("ACTIVE");
		vo.setPassword(bCryptPasswordEncoder.encode(vo.getPassword()));
		//vo.setFolders(new ArrayList<FolderVo>());
		this.userService.addUser(vo,ln);
	
		
	}
	
	@PutMapping(value = "/{username}")
	public void editUser(@PathVariable String username,@RequestBody AppUserVo vo) {
		this.userService.editUser(username, vo);
		
	}
	
	@PutMapping(value = "password/{username}")
	public void editPassword(@PathVariable String username,@RequestBody PasswordRequest vo) {
		this.userService.editPassword(username, vo);
		
	}
	
	@DeleteMapping(value = "/{username}")
	public ResponseEntity<Object> deleteUser(@PathVariable String username) {
		return null;
		
	}
	
	@GetMapping(value = "/{username}")
	public ResponseEntity<AppUserVo> getUser(@PathVariable String username) {
		return new ResponseEntity<AppUserVo>(this.userService.getVoUser(username),HttpStatus.OK);
		
	}
	
	@GetMapping()
	public ResponseEntity<Object> getUsersList() {
		return null;
		
	}
	
	@PutMapping(value = "resetPassword/{username}")
	public void resetPassword(@PathVariable String username,@RequestBody PasswordRequest vo) {
		this.userService.resetPassword(username, vo);
		
	}
	
	
	
	

}
