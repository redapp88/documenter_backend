package net.letapp.documenter.domaine;

import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppTokenVo {
	private String id;
	private String type;
	private Date creationDate;
	private Date exprDate;
	private Date usingDate;
	private AppUserVo user;
}
