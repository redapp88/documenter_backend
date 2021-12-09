package net.letapp.documenter.domaine;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.letapp.documenter.entities.DocumentType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentTypeVo  implements Serializable{

	private Long id;
	private String documentTypeName;
}
