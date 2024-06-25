package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.jpa.domain.Specification;

import com.cmrn_yng.sakilaflix.entities.Film;

public class FilmSpecs {

  public static Specification<Film> titleContains(String title) {
    return (root, query, builder) -> builder.like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
  }

  public static Specification<Film> categoryIs(String category) {
    return (root, query, builder) -> builder.equal(builder.lower(root.join("category").get("name")),
        category.toLowerCase());
  }

  public static Specification<Film> languageIs(String language) {
    return (root, query, builder) -> builder.equal(builder.lower(root.join("language").get("name")),
        language.toLowerCase());
  }
}
