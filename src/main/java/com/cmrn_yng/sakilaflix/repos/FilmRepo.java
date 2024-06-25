package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmrn_yng.sakilaflix.entities.Film;

public interface FilmRepo extends JpaRepository<Film, Short>, JpaSpecificationExecutor<Film> {

}