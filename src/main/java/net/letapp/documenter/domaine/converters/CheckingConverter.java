package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.CheckingVo;
import net.letapp.documenter.entities.Checking;

public class CheckingConverter {

	

	public static CheckingVo toVo(Checking bo) {
		CheckingVo vo = new CheckingVo();
		vo.setId(bo.getId());
		vo.setVisitor(bo.getVisitor());
		vo.setDate(bo.getDate());
		vo.setDetails(bo.getDetails());
		vo.setDocument(DocumentConverter.toVo(bo.getDocument()));
		return vo;
	}
	public static Checking toBo(CheckingVo vo) {
		Checking bo = new Checking();
		
		bo.setId(vo.getId());
		bo.setVisitor(vo.getVisitor());
		bo.setDate(vo.getDate());
		bo.setDetails(vo.getDetails());
		bo.setDocument(DocumentConverter.toBo(vo.getDocument()));
		return bo;
	}

	public static List<CheckingVo> toListVo(List<Checking> listBo) {
		List<CheckingVo> listVo = new ArrayList<CheckingVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<Checking> toListBo(List<CheckingVo> listVo) {
		List<Checking> listBo = new ArrayList<Checking>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
