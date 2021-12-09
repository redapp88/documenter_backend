package net.letapp.documenter.services;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.lang.Collections;
import net.letapp.documenter.dao.FolderRepository;
import net.letapp.documenter.domaine.AppUserVo;
import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.domaine.FolderVoPage;
import net.letapp.documenter.domaine.converters.DocumentTypeConverter;
import net.letapp.documenter.domaine.converters.FolderConverter;
import net.letapp.documenter.entities.AppUser;
import net.letapp.documenter.entities.Folder;

@Service
@Transactional
public class FolderServiceImp implements FolderService {
	@Autowired
	private FolderRepository forlderRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;

	@Override
	public void add(FolderVo vo) {
		AppUser user = this.userService.getUser(vo.getUser().getUsername());
		user.getFolders().forEach(folder -> {
			if (folder.getName().equals(vo.getName())) {
				throw new RuntimeException(this.environment.getProperty(this.environment.getProperty("errors.folders.error1")));
			}
		});
		this.forlderRepository.save(FolderConverter.toBo(vo));

	}

	@Override
	public void edit(Long id, FolderVo vo) {
		Folder folder = this.get(id);
		if (folder == null) {
			throw new RuntimeException(this.environment.getProperty("errors.folders.error2"));
		} else {
			folder.setName(vo.getName());
			folder.setDescription(vo.getDescription());
			folder.setType(DocumentTypeConverter.toBo(vo.getType()));
			this.forlderRepository.save(folder);
		}
	}

	@Override
	public void delete(Long id) {
		Folder folder = this.get(id);
		if (folder == null) {
			throw new RuntimeException(this.environment.getProperty("errors.folders.error2"));
		} else {
			this.forlderRepository.deleteById(id);
		}

	}

	@Override
	public FolderVo getVo(Long id) {
		Folder bo = this.get(id);
		if (bo == null)
			return null;
		else
			return FolderConverter.toVo(bo);
	}

	@Override
	public FolderVoPage getAll(String username, String keyword, int page, int size) {
		if (keyword.equals("*"))
			keyword = "";
		keyword = "%" + keyword + "%";

		Page<Folder> loadedpage = this.forlderRepository.findByKeyword(username, keyword,
				PageRequest.of(page, size, Direction.DESC, "creationDate"));
		FolderVoPage folderVoPage = new FolderVoPage();
		folderVoPage.setContent(FolderConverter.toListVo(loadedpage.getContent()));
		folderVoPage.setNumberOfElements(loadedpage.getTotalElements());
		folderVoPage.setPageSize(loadedpage.getSize());
		return folderVoPage;
	}

	@Override
	public Folder get(Long id) {
		Optional<Folder> opt = this.forlderRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			return null;

	}

	@Override
	public List<FolderVo> getTopChecks(String username) {
		List<Folder> folders = this.forlderRepository.findByUserName(username);
		List<FolderVo> foldersVo = new ArrayList<FolderVo>();
		folders.forEach(folder -> {
			FolderVo tempFolderVo = new FolderVo();
			folder.getDocuments().forEach(document -> {
				tempFolderVo.setChecksCount(document.getChecks().size() + tempFolderVo.getChecksCount());
			});
			FolderVo folderVo = FolderConverter.toVo(folder);
			folderVo.setChecksCount(tempFolderVo.getChecksCount());
			foldersVo.add(folderVo);
		});
		java.util.Collections.sort(foldersVo);
		return foldersVo.stream().limit(Integer.parseInt(environment.getProperty("my.topFolders")))
				.collect(Collectors.toList());
	}

}
