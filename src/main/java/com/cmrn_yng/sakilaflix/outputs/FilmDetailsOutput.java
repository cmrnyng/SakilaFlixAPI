package com.cmrn_yng.sakilaflix.outputs;

import com.cmrn_yng.sakilaflix.entities.Film;

import lombok.Getter;

@Getter
public class FilmDetailsOutput {
  private final Short id;
  private final String title;
  private final String description;
  private final Integer releaseYear;
  private final Byte languageId;
  private final Short length;
  private final String rating;

  public FilmDetailsOutput(Film film) {
    this.id = film.getId();
    this.title = film.getTitle();
    this.description = film.getDescription();
    this.releaseYear = film.getReleaseYear();
    this.languageId = film.getLanguageId();
    this.length = film.getLength();
    this.rating = film.getRating();
  }
}
