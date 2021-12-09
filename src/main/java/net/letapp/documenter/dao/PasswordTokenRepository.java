package net.letapp.documenter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.letapp.documenter.entities.PasswordToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, String> {

}
