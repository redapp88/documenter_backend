package net.letapp.documenter.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.Data;

@Data
public class ExtendedUser extends User {
	private String name;
	private String state;


	public ExtendedUser(String username, String password, String name,String state,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.name = name;
		this.state=state;


	}

}
