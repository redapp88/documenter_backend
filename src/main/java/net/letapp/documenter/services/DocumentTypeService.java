package net.letapp.documenter.services;

import java.util.List;

import net.letapp.documenter.domaine.DocumentTypeVo;
import net.letapp.documenter.entities.DocumentType;

public interface DocumentTypeService {

	public void add(DocumentTypeVo vo);

	public void edit(Long id, DocumentTypeVo vo);

	public void delete(Long id);

	public List<DocumentTypeVo> getAll();

	public DocumentTypeVo getVo(Long id);
	public DocumentType getDocumentType(Long id);

}
