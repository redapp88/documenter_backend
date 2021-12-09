package net.letapp.documenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.letapp.documenter.entities.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

}
