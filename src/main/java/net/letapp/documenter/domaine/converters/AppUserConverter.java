package net.letapp.documenter.domaine.converters;

import java.util.ArrayList;
import java.util.List;

import net.letapp.documenter.domaine.AppUserVo;
import net.letapp.documenter.entities.AppUser;

public class AppUserConverter {
	public static AppUserVo toVo(AppUser bo) {
		AppUserVo vo = new AppUserVo();
		vo.setUsername(bo.getUsername());
		vo.setPassword(bo.getPassword());
		vo.setName(bo.getName());
		vo.setDescription(bo.getDescription());
		vo.setContry(bo.getContry());
		vo.setCity(bo.getCity());
		vo.setAdress(bo.getAdress());
		vo.setPostalCode(bo.getPostalCode());
		vo.setSubDate(bo.getSubDate());
		vo.setCategory(CategoryConverter.toVo(bo.getCategory()));
		vo.setState(bo.getState());
		vo.setAppRole(AppRoleConverter.toVo(bo.getAppRole()));
		//vo.setFolders(FolderConverter.toListVo(bo.getFolders()));
		
		return vo;
	}
	public static AppUser toBo(AppUserVo vo) {
		AppUser bo = new AppUser();

		bo.setUsername(vo.getUsername());
		bo.setPassword(vo.getPassword());
		bo.setName(vo.getName());
		bo.setDescription(vo.getDescription());
		bo.setContry(vo.getContry());
		bo.setCity(vo.getCity());
		bo.setAdress(vo.getAdress());
		bo.setPostalCode(vo.getPostalCode());
		bo.setSubDate(vo.getSubDate());
		bo.setCategory(CategoryConverter.toBo(vo.getCategory()));
		bo.setState(vo.getState());
		bo.setAppRole(AppRoleConverter.toBo(vo.getAppRole()));
		//bo.setFolders(FolderConverter.toListBo(vo.getFolders()));
		return bo;
	}

	public static List<AppUserVo> toListVo(List<AppUser> listBo) {
		List<AppUserVo> listVo = new ArrayList<AppUserVo>();
		listBo.forEach(bo->{
			listVo.add(toVo(bo));
		});
		return listVo;
		
	}

	public static List<AppUser> toListBo(List<AppUserVo> listVo) {
		List<AppUser> listBo = new ArrayList<AppUser>();
		listVo.forEach(vo->{
			listBo.add(toBo(vo));
		});
		return listBo;
		
	}
}
