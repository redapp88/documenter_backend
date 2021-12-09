package net.letapp.documenter.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.letapp.documenter.domaine.AppUserVo;
import net.letapp.documenter.domaine.DocumentPageVo;
import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.domaine.FolderVoPage;
import net.letapp.documenter.services.DocumentService;
import net.letapp.documenter.services.FolderService;
import net.letapp.documenter.services.UserService;

@RestController
@RequestMapping(path = "/documents")
public class DocumentsController {
	@Autowired
	private DocumentService documentService;
	@Autowired
	private UserService UserService;
	
	@PostMapping
	public DocumentVo addDocument(@RequestBody DocumentVo vo){
		  String username=SecurityContextHolder. getContext(). getAuthentication(). getPrincipal().toString();
		  vo.setCreatDate(new Date());
		  AppUserVo userVo = this.UserService.getVoUser(username);;
		  //vo.setImages(new ArrayList()); 
		  //vo.setChecks(new ArrayList<>());
		  return this.documentService.add(vo);
	
	}
	
	@PutMapping(value="/{id}")
	public DocumentVo editDocument(@PathVariable("id") String id,@RequestBody DocumentVo vo){
		  String username=SecurityContextHolder. getContext(). getAuthentication(). getPrincipal().toString();
		  AppUserVo userVo = this.UserService.getVoUser(username);;
		  //vo.setImages(new ArrayList()); 
		  //vo.setChecks(new ArrayList<>());
		  return this.documentService.edit(id,vo);
	
	}
	
	@PostMapping("/delete")
	public void deleteDocument(@RequestBody List<String> ids){

		  String username=SecurityContextHolder. getContext(). getAuthentication(). getPrincipal().toString();
		 this.documentService.delete(ids);
	
	}
	
	

	@GetMapping()
	public ResponseEntity<DocumentPageVo> documentList(@RequestParam Long folderId,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "*") String keyword){
		 DocumentPageVo documentPageVo = this.documentService.getAll(folderId,keyword,page,size);
		 return new ResponseEntity<DocumentPageVo>(documentPageVo,HttpStatus.OK);
	}
	
	
	
	/*
	 * 	@GetMapping(value = "/{id}")
	public FolderVo getFolder(@PathVariable(value = "id") Long id){
		 FolderVo vo = this.folderService.getVo(id);
		 return  vo;
	}
	
	 * @PutMapping(value = "/{id}") public void editDocument(@PathVariable(value =
	 * "id")Long id,@RequestBody FolderVo vo){ this.folderService.edit(id,vo); }
	 * 
	 * @DeleteMapping(value = "/{id}") public void deleteFolder(@PathVariable(value
	 * = "id") Long id){ this.folderService.delete(id);
	 * 
	 * }
	 */
}
