package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.entities.Category;


public class CategoryConverter {

	public static CategoryVo toVo(Category bo) {
		CategoryVo vo = new CategoryVo();
		vo.setId(bo.getId());
		vo.setCategoryName(bo.getCategoryName());
		return vo;
	}
	public static Category toBo(CategoryVo vo) {
		Category bo = new Category();
		bo.setId(vo.getId());
		bo.setCategoryName(vo.getCategoryName());
		return bo;
	}

	public static List<CategoryVo> toListVo(List<Category> listBo) {
		List<CategoryVo> listVo = new ArrayList<CategoryVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<Category> toListBo(List<CategoryVo> listVo) {
		List<Category> listBo = new ArrayList<Category>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
