package net.letapp.documenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.letapp.documenter.domaine.AppRoleVo;
import net.letapp.documenter.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

	public AppRole findByRoleName(String roleName);

}
