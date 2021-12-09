package net.letapp.documenter.services;

import java.util.List;

import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.domaine.FolderVoPage;
import net.letapp.documenter.entities.Folder;

public interface FolderService {

	public void add(FolderVo vo);

	public void edit(Long id, FolderVo vo);

	public void delete(Long id);

	public FolderVo getVo(Long id);

	public FolderVoPage getAll(String username, String keyword, int page,int size);

	public Folder get(Long id);

	public List<FolderVo> getTopChecks(String username);

}
