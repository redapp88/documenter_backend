package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.PasswordTokenVo;
import net.letapp.documenter.entities.PasswordToken;

public class PasswordTokenConverter {
	public static PasswordTokenVo toVo(PasswordToken bo) {
		PasswordTokenVo vo = new PasswordTokenVo();
	
		vo.setId(bo.getId());
		vo.setExpDate(bo.getExpDate());
		vo.setCreationDate(bo.getExpDate());
		vo.setNewPassword(bo.getNewPassword());
		vo.setUsername(bo.getUser().getUsername());
		return vo;
	}
	public static PasswordToken toBo(PasswordTokenVo vo) {
		PasswordToken bo = new PasswordToken();
		bo.setId(vo.getId());
		bo.setExpDate(vo.getExpDate());
		bo.setCreationDate(vo.getExpDate());
		bo.setNewPassword(vo.getNewPassword());
		return bo;
	}

	public static List<PasswordTokenVo> toListVo(List<PasswordToken> listBo) {
		List<PasswordTokenVo> listVo = new ArrayList<PasswordTokenVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<PasswordToken> toListBo(List<PasswordTokenVo> listVo) {
		List<PasswordToken> listBo = new ArrayList<PasswordToken>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
