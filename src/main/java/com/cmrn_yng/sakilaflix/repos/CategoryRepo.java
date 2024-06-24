package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmrn_yng.sakilaflix.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Byte> {

}
