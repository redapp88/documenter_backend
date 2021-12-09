package net.letapp.documenter.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import net.letapp.documenter.domaine.CategoryVo;

public interface CategoryService {

	public void add(CategoryVo vo);

	public void edit(Long id, CategoryVo vo);

	public void delete(Long id);

	public List<CategoryVo> getAll();

	public CategoryVo getVo(Long id);

}
