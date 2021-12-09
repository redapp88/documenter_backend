package net.letapp.documenter.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Document {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String title;
	private String description;
	private Date creatDate;
	@ManyToOne
	private DocumentType docmentType;
	@OneToMany(mappedBy = "document",cascade = CascadeType.ALL)
	
	private List<Image> images = new ArrayList<Image>();
	@ManyToOne
	private Folder folder;
	@OneToMany(mappedBy = "document")
	private List<Checking> checks = new ArrayList<Checking>();
	public Document(String title, String description, Date creatDate, DocumentType docmentType, Folder folder) {
		super();
		this.title = title;
		this.description = description;
		this.creatDate = creatDate;
		this.docmentType = docmentType;
		this.folder = folder;
	}
	
	
	
}
