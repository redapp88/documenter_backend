package net.letapp.documenter.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import net.letapp.documenter.domaine.MonthChecksVo;
import net.letapp.documenter.services.DocumentService;
import net.letapp.documenter.services.FolderService;
import net.letapp.documenter.services.UserService;

@RestController
@RequestMapping("checks")
public class ChecksController {
	@Autowired
	private FolderService folderService;
	@Autowired
	private UserService UserService;
	@Autowired
	private DocumentService documentService;
	
	
	
	@GetMapping("/folders")
	public ResponseEntity<List<FolderVo>> folderWithChecks(@RequestParam String username){
		 List<FolderVo> FoldersList = this.folderService.getTopChecks(username);
		 return new ResponseEntity<List<FolderVo>>(FoldersList,HttpStatus.OK);
	}
	
	@GetMapping("/documents")
	public ResponseEntity<List<DocumentVo>> documentsWithChecks(@RequestParam String username){
		 List<DocumentVo> documentsList = this.documentService.getTopChecks(username);
		 return new ResponseEntity<List<DocumentVo>>(documentsList,HttpStatus.OK);
	}
	
	@GetMapping("/yearChecks")
	public ResponseEntity<List<MonthChecksVo>> yearChecks(@RequestParam String username){
		 List<MonthChecksVo> MonthChecksList = this.documentService.yearChecks(username);
		 return new ResponseEntity<List<MonthChecksVo>>(MonthChecksList,HttpStatus.OK);
	}
	
	@GetMapping("/check")
	public ResponseEntity<DocumentVo> checkDocument(@RequestParam String documentId,HttpServletRequest request){
		 DocumentVo vo = this.documentService.checkDocument(documentId,request);
		 return new ResponseEntity<DocumentVo>(vo,HttpStatus.OK);
	}
	

}
