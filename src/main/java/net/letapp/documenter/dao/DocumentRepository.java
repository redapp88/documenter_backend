package net.letapp.documenter.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.letapp.documenter.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, String> {
	@Query("Select d from Document d where d.folder.id = :folderId and d.title like :keyword")
	public Page<Document> findByKeyword(
			@Param(value="folderId")Long folderId,
			@Param(value="keyword")String keyword,
			Pageable pageable);
	@Query("Select d from Document d where d.folder.user.username like :username order by d.checks.size desc")
	public List<Document> getTopChecks(
			@Param(value="username")String username);
	
	

}
