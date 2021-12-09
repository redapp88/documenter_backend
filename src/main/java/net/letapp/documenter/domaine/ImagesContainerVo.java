package net.letapp.documenter.domaine;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesContainerVo {
	private String documentId;
	private List<ImageVo> images;

}
