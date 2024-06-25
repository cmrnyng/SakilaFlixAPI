package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmrn_yng.sakilaflix.entities.Film;

public interface FilmRepo extends JpaRepository<Film, Short> {
  @Query("SELECT f FROM Film f JOIN f.category c WHERE LOWER(c.name) = LOWER(:category)")
  Page<Film> findByCategoryNameIgnoreCase(@Param("category") String category, Pageable pageable);

  Page<Film> findByTitleContainingIgnoreCase(String title, Pageable pageable);

  @Query("SELECT f FROM Film f JOIN f.category c WHERE LOWER(c.name) = LOWER(:category) AND LOWER(f.title) LIKE LOWER(CONCAT('%', :title, '%'))")
  Page<Film> findByTitleContainingIgnoreCaseAndCategoryNameIgnoreCase(@Param("title") String title,
      @Param("category") String category, Pageable pageable);
}