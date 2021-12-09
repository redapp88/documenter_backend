package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.AppTokenVo;
import net.letapp.documenter.entities.AppToken;

public class AppTokenConverter {
	public static AppTokenVo toVo(AppToken bo) {
		AppTokenVo vo = new AppTokenVo();
		vo.setId(bo.getId());
		vo.setType(bo.getType());
		vo.setCreationDate(bo.getCreationDate());
		vo.setUsingDate(bo.getUsingDate());
		vo.setExprDate(bo.getExprDate());
		vo.setUser(AppUserConverter.toVo(bo.getUser()));
		return vo;
	}
	public static AppToken toBo(AppTokenVo vo) {
		AppToken bo = new AppToken();
		bo.setId(vo.getId());
		bo.setType(vo.getType());
		bo.setCreationDate(vo.getCreationDate());
		bo.setUsingDate(vo.getUsingDate());
		bo.setExprDate(vo.getExprDate());
		bo.setUser(AppUserConverter.toBo(vo.getUser()));
		return bo;
	}

	public static List<AppTokenVo> toListVo(List<AppToken> listBo) {
		List<AppTokenVo> listVo = new ArrayList<AppTokenVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<AppToken> toListBo(List<AppTokenVo> listVo) {
		List<AppToken> listBo = new ArrayList<AppToken>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
