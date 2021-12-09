package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.DocumentTypeVo;
import net.letapp.documenter.entities.DocumentType;

public class DocumentTypeConverter {

	
	public static DocumentTypeVo toVo(DocumentType bo) {
		DocumentTypeVo vo = new DocumentTypeVo();
		vo.setId(bo.getId());
		vo.setDocumentTypeName(bo.getDocumentTypeName());
		return vo;
	}
	public static DocumentType toBo(DocumentTypeVo vo) {
		DocumentType bo = new DocumentType();
		bo.setId(vo.getId());
		bo.setDocumentTypeName(vo.getDocumentTypeName());
		return bo;
	}

	public static List<DocumentTypeVo> toListVo(List<DocumentType> listBo) {
		List<DocumentTypeVo> listVo = new ArrayList<DocumentTypeVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<DocumentType> toListBo(List<DocumentTypeVo> listVo) {
		List<DocumentType> listBo = new ArrayList<DocumentType>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
