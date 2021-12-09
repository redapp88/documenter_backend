package net.letapp.documenter.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.letapp.documenter.entities.Folder;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUserVo {

	private String username;
	private String password;
	private String name;
	private String description;
	private String contry;
	private String city;
	private String adress;
	private String postalCode;
	private Date subDate;
	private CategoryVo category;
	private String state;
	private AppRoleVo appRole;

}
