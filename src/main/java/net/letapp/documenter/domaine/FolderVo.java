package net.letapp.documenter.domaine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FolderVo implements Comparable{

	private Long id;
	private String name;
	private String description;
	@JsonIgnore
	private AppUserVo user;
	private DocumentTypeVo type;;
	private Date creationDate;
	 //@JsonIgnore
	//private List<DocumentVo> documents=new ArrayList<>();
	private int documentsCount;
	private int checksCount;
	@Override
	public int compareTo(Object o) {
	return this.checksCount- ((FolderVo)o).getChecksCount();
	}
}
