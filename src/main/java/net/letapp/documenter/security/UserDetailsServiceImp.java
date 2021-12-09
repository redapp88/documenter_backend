package net.letapp.documenter.security;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.letapp.documenter.entities.AppUser;
import net.letapp.documenter.services.UserService;


@Service
@Transactional

public class UserDetailsServiceImp implements UserDetailsService {
@Autowired
private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser= this.userService.getUser(username);
		if (appUser== null) throw new UsernameNotFoundException(username);
		if(appUser.getState().equals("DELETED"))  throw new RuntimeException("Compte supprimé");
		if(appUser.getState().equals("DISABLED")) throw new RuntimeException("compte desactivé");
		Collection<GrantedAuthority> authorities=new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(appUser.getAppRole().getRoleName()));
	    ExtendedUser user=new ExtendedUser(
	    		appUser.getUsername(),
	    		appUser.getPassword(),
	    		appUser.getName(),
	    		appUser.getState(),
	    		authorities);
		return user;
	}



}
