package net.letapp.documenter.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.letapp.documenter.dao.CategoryRepository;
import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.domaine.converters.CategoryConverter;
import net.letapp.documenter.entities.Category;
@Service
@Transactional
public class CategoryServiceImp implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;
@Autowired
Environment environment;
	@Override
	public void add(CategoryVo vo) {
		Category bo = CategoryConverter.toBo(vo);
		this.categoryRepository.save(bo);

	}

	@Override
	public void edit(Long id, CategoryVo vo) {
		Category bo = this.getCategory(id);
		bo.setCategoryName(vo.getCategoryName());
		this.categoryRepository.save(bo);

	}

	@Override
	public void delete(Long id) {
		Category bo = this.getCategory(id);
		this.categoryRepository.delete(bo);

	}

	@Override
	public List<CategoryVo> getAll() {
	return CategoryConverter.toListVo(this.categoryRepository.findAll());
	}
	public Category getCategory(Long id) {
		Optional<Category> opt = this.categoryRepository.findById(id);
		if(!opt.isPresent())
			throw new RuntimeException(this.environment.getProperty("errors.categories.error1"));
		return opt.get();
	}

	@Override
	public CategoryVo getVo(Long id) {
	Category bo = this.getCategory(id);
	return CategoryConverter.toVo(bo);
	}

}
