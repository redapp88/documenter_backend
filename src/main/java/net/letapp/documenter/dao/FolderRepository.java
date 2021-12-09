package net.letapp.documenter.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.letapp.documenter.domaine.FolderVo;
import net.letapp.documenter.entities.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
	@Query("Select f from Folder f where f.name like :keyword and f.user.username like :username")
	public Page<Folder> findByKeyword(
			@Param(value="username")String username,
			@Param(value="keyword")String keyword,
			Pageable pageable);
	

	@Query("Select f from Folder f where f.user.username like :username")
	public List<Folder> findByUserName(
			@Param(value="username")String username);
	

}
