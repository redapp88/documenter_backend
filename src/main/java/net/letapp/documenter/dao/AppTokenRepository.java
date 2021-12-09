package net.letapp.documenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.letapp.documenter.entities.AppToken;

public interface AppTokenRepository extends JpaRepository<AppToken, String> {

}
