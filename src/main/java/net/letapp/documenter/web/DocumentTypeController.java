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

import net.letapp.documenter.domaine.DocumentTypeVo;
import net.letapp.documenter.services.DocumentTypeService;

@RestController
@RequestMapping("dtypes")
public class DocumentTypeController {
@Autowired
private DocumentTypeService documentTypeSerice;
	@PostMapping
	public ResponseEntity<Object> addDocumentType(@RequestBody DocumentTypeVo vo){
		//System.out.println(vo.getDocumentTypeName());
		 this.documentTypeSerice.add(vo);
		 return new ResponseEntity<Object>("DocumentType Added successfully",HttpStatus.OK);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> editDocumentType(@PathVariable(value = "id")Long id,@RequestBody DocumentTypeVo vo){
		 this.documentTypeSerice.edit(id,vo);
		 return new ResponseEntity<Object>("DocumentType updated successfully",HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteDocumentType(@PathVariable(value = "id") Long id){
		 this.documentTypeSerice.delete(id);
		 return new ResponseEntity<Object>("DocumentType deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getDocumentType(@PathVariable(value = "id") Long id){
		 DocumentTypeVo vo = this.documentTypeSerice.getVo(id);
		 return new ResponseEntity<Object>(vo,HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<Object> DocumentTypeList(){
		 List<DocumentTypeVo> categories = this.documentTypeSerice.getAll();
		 return new ResponseEntity<Object>(categories,HttpStatus.OK);
	}
	
}
