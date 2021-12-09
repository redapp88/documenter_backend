package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.ImageVo;
import net.letapp.documenter.entities.Image;

public class ImageConverter {

	public static ImageVo toVo(Image bo) {
		ImageVo vo = new ImageVo();
	
		vo.setId(bo.getId());
		vo.setUrl(bo.getUrl());
		vo.setOrdre(bo.getOrdre());
		vo.setDocument(DocumentConverter.toVo(bo.getDocument()));
		return vo;
	}
	public static Image toBo(ImageVo vo) {
		Image bo = new Image();
		bo.setId(vo.getId());
		bo.setUrl(vo.getUrl());
		bo.setOrdre(bo.getOrdre());
		if(vo.getDocument() != null)
		bo.setDocument(DocumentConverter.toBo(vo.getDocument()));
		return bo;
	}

	public static List<ImageVo> toListVo(List<Image> listBo) {
		List<ImageVo> listVo = new ArrayList<ImageVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<Image> toListBo(List<ImageVo> listVo) {
		List<Image> listBo = new ArrayList<Image>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
