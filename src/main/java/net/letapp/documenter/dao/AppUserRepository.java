package net.letapp.documenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.letapp.documenter.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

}
