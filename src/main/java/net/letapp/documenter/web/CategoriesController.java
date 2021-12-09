package net.letapp.documenter.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;
	@PostMapping
	public ResponseEntity<Object> addCategory(@RequestBody CategoryVo vo){
		//System.out.println(vo.getCategoryName());
		 this.categoryService.add(vo);
		 return new ResponseEntity<Object>("Category Added successfully",HttpStatus.OK);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> editCategory(@PathVariable(value = "id")Long id,@RequestBody CategoryVo vo){
		 this.categoryService.edit(id,vo);
		 return new ResponseEntity<Object>("Category updated successfully",HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") Long id){
		 this.categoryService.delete(id);
		 return new ResponseEntity<Object>("Category deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getCategory(@PathVariable(value = "id") Long id){
		 CategoryVo vo = this.categoryService.getVo(id);
		 return new ResponseEntity<Object>(vo,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<Object> categoryList(){
		 List<CategoryVo> categories = this.categoryService.getAll();
		 return new ResponseEntity<Object>(categories,HttpStatus.OK);
	}
	
	
	
}
