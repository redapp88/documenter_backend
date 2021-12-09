package net.letapp.documenter.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordRequest {
	private String currentPassword;
	private String password;
	private String repassword;
	private String ln;
	private String tokenId;
}
