package net.letapp.documenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.letapp.documenter.entities.Checking;

public interface CheckingRespository extends JpaRepository<Checking, Long> {

	@Query("Select c from Checking c where c.document.folder.user.username like :username and month(c.date)=:month and year(c.date)=:year")
	public List<Checking> getChecksMonth(String username, Integer month, Integer year);
}
