package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmrn_yng.sakilaflix.entities.Film;

public interface FilmRepo extends JpaRepository<Film, Short> {

}
