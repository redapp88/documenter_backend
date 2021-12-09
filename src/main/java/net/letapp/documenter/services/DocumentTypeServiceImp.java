package net.letapp.documenter.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.letapp.documenter.dao.DocumentTypeRepository;
import net.letapp.documenter.domaine.DocumentTypeVo;
import net.letapp.documenter.domaine.converters.DocumentTypeConverter;
import net.letapp.documenter.entities.DocumentType;
@Service
@Transactional
public class DocumentTypeServiceImp implements DocumentTypeService {
@Autowired
private DocumentTypeRepository documentTypeRespository;
@Autowired
private Environment environment;
	@Override
	public void add(DocumentTypeVo vo) {
		List<DocumentType> documentTypes = this.documentTypeRespository.findAll();
		documentTypes.forEach(dt->{
			if(dt.getDocumentTypeName().equals(vo.getDocumentTypeName()))
				throw new RuntimeException("Document Type exists already");
		});
		this.documentTypeRespository.save(DocumentTypeConverter.toBo(vo));

	}

	@Override
	public void edit(Long id, DocumentTypeVo vo) {
	DocumentType bo = this.getDocumentType(id);
	if(bo != null) {
		List<DocumentType> documentTypes = this.documentTypeRespository.findAll();
		documentTypes.forEach(dt->{
			if(dt.getDocumentTypeName().equals(vo.getDocumentTypeName()))
				throw new RuntimeException(this.environment.getProperty("errors.documentstype.error1"));
		});
		bo.setDocumentTypeName(vo.getDocumentTypeName());
		this.documentTypeRespository.save(bo);
	}
	else {
		throw new RuntimeException(this.environment.getProperty("errors.documentstype.error2"));
	}

	}

	@Override
	public void delete(Long id) {
		DocumentType bo = this.getDocumentType(id);
		if(bo != null) {
		
			this.documentTypeRespository.deleteById(id);
		}
		else {
			throw new RuntimeException(this.environment.getProperty("errors.documentstype.error2"));
		}

	}

	@Override
	public List<DocumentTypeVo> getAll() {
		return DocumentTypeConverter.toListVo(this.documentTypeRespository.findAll());
	}

	@Override
	public DocumentTypeVo getVo(Long id) {
		DocumentType bo = this.getDocumentType(id);
		if (bo != null)
			return DocumentTypeConverter.toVo(bo);
		else
			throw new RuntimeException(this.environment.getProperty("errors.documentstype.error2"));
	}

	@Override
	public DocumentType getDocumentType(Long id) {
		Optional<DocumentType> opt = this.documentTypeRespository.findById(id);
		if(opt.isPresent())
			return opt.get();
		return null;
	}

}
