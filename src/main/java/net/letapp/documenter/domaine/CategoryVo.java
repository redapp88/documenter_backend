package net.letapp.documenter.domaine;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.letapp.documenter.entities.Category;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryVo implements Serializable {

	private Long id;
	private String categoryName;
}
