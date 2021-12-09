package net.letapp.documenter.domaine;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.letapp.documenter.entities.Document;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageVo implements Serializable {
	private Long id;
	private String url;
	private int ordre;
	private DocumentVo document;
}
