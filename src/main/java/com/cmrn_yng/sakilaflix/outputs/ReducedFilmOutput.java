package com.cmrn_yng.sakilaflix.outputs;

import com.cmrn_yng.sakilaflix.entities.Film;

import lombok.Getter;

@Getter
public class ReducedFilmOutput {
  private final Short id;
  private final String title;

  public ReducedFilmOutput(Film film) {
    this.id = film.getId();
    this.title = film.getTitle();
  }
}
