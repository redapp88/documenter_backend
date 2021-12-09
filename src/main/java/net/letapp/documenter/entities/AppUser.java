package net.letapp.documenter.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class AppUser {
	@Id
	private String username;
	private String password;
	private String name;
	private String description;
	private String contry;
	private String city;
	private String adress;
	private Date subDate;
	private String postalCode;
	@ManyToOne
	private Category category;
	private String state;
	@ManyToOne
	private AppRole appRole;
	@OneToMany(mappedBy = "user")
	private List<Folder> folders=new ArrayList<>();
}
