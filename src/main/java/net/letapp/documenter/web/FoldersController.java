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
import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.domaine.FolderVoPage;
import net.letapp.documenter.services.FolderService;
import net.letapp.documenter.services.UserService;

@RestController
@RequestMapping("folders")
public class FoldersController {
	@Autowired
	private FolderService folderService;
	@Autowired
	private UserService UserService;
	
	@PostMapping
	public void addFolder(@RequestBody FolderVo vo){

		  String username=SecurityContextHolder. getContext(). getAuthentication(). getPrincipal().toString();
		  AppUserVo userVo = this.UserService.getVoUser(username);;
		  vo.setUser(userVo); 
		  //vo.setDocuments(new ArrayList<DocumentVo>());
		  vo.setCreationDate(new Date());
			//System.out.println(vo);
		  this.folderService.add(vo);
	
	}
	@PutMapping(value = "/{id}")
	public void editFolder(@PathVariable(value = "id")Long id,@RequestBody FolderVo vo){
		 this.folderService.edit(id,vo);
	}
	@DeleteMapping(value = "/{id}")
	public void deleteFolder(@PathVariable(value = "id") Long id){
		 this.folderService.delete(id);
		
	}
	
	@GetMapping(value = "/{id}")
	public FolderVo getFolder(@PathVariable(value = "id") Long id){
		 FolderVo vo = this.folderService.getVo(id);
		 return  vo;
	}
	@GetMapping()
	public ResponseEntity<Object> folderList(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "") String keyword){
		 String username=SecurityContextHolder. getContext(). getAuthentication(). getPrincipal().toString();
		 FolderVoPage FolderVoPage = this.folderService.getAll(username,keyword,page,size);
		 return new ResponseEntity<Object>(FolderVoPage,HttpStatus.OK);
	}
	

}
