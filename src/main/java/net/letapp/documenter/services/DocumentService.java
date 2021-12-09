package net.letapp.documenter.services;

import java.net.http.HttpHeaders;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.letapp.documenter.domaine.DocumentPageVo;
import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.domaine.MonthChecksVo;
import net.letapp.documenter.entities.Document;

public interface DocumentService {

	public DocumentVo add(DocumentVo vo);

	public DocumentPageVo getAll(Long folderId, String keyword, int page, int size);

	public void delete(List<String> ids);
	
	public Document get(String id);

	public DocumentVo edit(String id, DocumentVo vo);
	
	public List<DocumentVo> getTopChecks(String username);

	public List<MonthChecksVo> yearChecks(String username);


	public DocumentVo checkDocument(String documentId,HttpServletRequest request);

}
