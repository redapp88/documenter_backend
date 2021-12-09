package net.letapp.documenter.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.letapp.documenter.entities.AppUser;
import net.letapp.documenter.entities.Checking;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentVo {

	private String id;
	private String title;
	private String description;
	private Date creatDate;
	private DocumentTypeVo documentType;
	private int imagesCount;
	private int checksCount;
	//private List<ImageVo> images = new ArrayList<ImageVo>();
	private FolderVo folder;
	//private List<CheckingVo> checks = new ArrayList<CheckingVo>();
}
