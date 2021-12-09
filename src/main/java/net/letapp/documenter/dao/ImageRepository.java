package net.letapp.documenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.letapp.documenter.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	@Query("select i from Image i where i.document.id like :documentId")
	public List<Image> findAllByDocumentId
	(@Param(value = "documentId") String documentId);

}
