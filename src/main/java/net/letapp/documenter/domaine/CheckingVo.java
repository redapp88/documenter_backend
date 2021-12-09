package net.letapp.documenter.domaine;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.letapp.documenter.entities.Document;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckingVo {

	private Long id;
	private String visitor;
	private Date date;
	private String details;
	private DocumentVo document;
}
