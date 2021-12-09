package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.entities.Folder;

public class FolderConverter {
	
	public static FolderVo toVo(Folder bo) {
		FolderVo vo = new FolderVo();
		vo.setId(bo.getId());
		vo.setName(bo.getName());
		vo.setDescription(bo.getDescription());
		if(bo.getUser() != null)
		vo.setUser(AppUserConverter.toVo(bo.getUser()));
		vo.setCreationDate(bo.getCreationDate());
		vo.setDocumentsCount(bo.getDocuments().size());
		if(bo.getType() != null)
		vo.setType(DocumentTypeConverter.toVo(bo.getType()));
		return vo;
	}
	public static Folder toBo(FolderVo vo) {
		Folder bo = new Folder();
		bo.setId(vo.getId());
		bo.setName(vo.getName());
		bo.setDescription(vo.getDescription());
		if(vo.getUser()!=null) {
		bo.setUser(AppUserConverter.toBo(vo.getUser()));}
		bo.setCreationDate(vo.getCreationDate());
		//bo.setDocuments(DocumentConverter.toListBo(vo.getDocuments()));
		if(vo.getType()!=null) {
		bo.setType(DocumentTypeConverter.toBo(vo.getType()));
		}

		return bo;
	}

	public static List<FolderVo> toListVo(List<Folder> listBo) {
		List<FolderVo> listVo = new ArrayList<FolderVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<Folder> toListBo(List<FolderVo> listVo) {
		List<Folder> listBo = new ArrayList<Folder>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}

}
