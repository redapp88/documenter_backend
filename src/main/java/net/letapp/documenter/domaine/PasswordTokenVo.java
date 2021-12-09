package net.letapp.documenter.domaine;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordTokenVo {
	private String id;
	private Date creationDate;
	private Date expDate;
	private String Username;
	private String newPassword;
	private String type;
	private String ln;

}
