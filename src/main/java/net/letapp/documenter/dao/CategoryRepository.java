package net.letapp.documenter.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import net.letapp.documenter.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
