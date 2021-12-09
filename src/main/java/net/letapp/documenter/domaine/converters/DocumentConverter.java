package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.entities.Document;

public class DocumentConverter {

	public static DocumentVo toVo(Document bo) {
		DocumentVo vo = new DocumentVo();
		vo.setId(bo.getId());
		vo.setTitle(bo.getTitle());
		vo.setDescription(bo.getDescription());
		vo.setCreatDate(bo.getCreatDate());
		vo.setDocumentType(DocumentTypeConverter.toVo(bo.getDocmentType()));
		vo.setFolder(FolderConverter.toVo(bo.getFolder()));
		vo.setChecksCount(bo.getChecks().size());
		vo.setImagesCount(bo.getImages().size());

		return vo;
	}
	public static Document toBo(DocumentVo vo) {
		Document bo = new Document();
		
		bo.setId(vo.getId());
		bo.setTitle(vo.getTitle());
		bo.setDescription(vo.getDescription());
		bo.setCreatDate(vo.getCreatDate());
		bo.setDocmentType(DocumentTypeConverter.toBo(vo.getDocumentType()));
		if(vo.getFolder()!=null) {
		bo.setFolder(FolderConverter.toBo(vo.getFolder()));}
		
		//bo.setChecks(CheckingConverter.toListBo(vo.getChecks()));
		
		return bo;
	}

	public static List<DocumentVo> toListVo(List<Document> listBo) {
		List<DocumentVo> listVo = new ArrayList<DocumentVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<Document> toListBo(List<DocumentVo> listVo) {
		List<Document> listBo = new ArrayList<Document>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
