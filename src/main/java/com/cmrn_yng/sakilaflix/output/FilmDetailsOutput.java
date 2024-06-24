package com.cmrn_yng.sakilaflix.output;

import java.util.Set;
import java.util.stream.Collectors;

import com.cmrn_yng.sakilaflix.entities.Category;
import com.cmrn_yng.sakilaflix.entities.Film;
import com.cmrn_yng.sakilaflix.entities.Language;

import lombok.Getter;

@Getter
public class FilmDetailsOutput {
  private final Short id;
  private final String title;
  private final String description;
  private final Integer releaseYear;
  private final Language language;
  private final Short length;
  private final String rating;
  private final Category category;
  private final Set<ReducedActorOutput> cast;

  public FilmDetailsOutput(Film film) {
    this.id = film.getId();
    this.title = film.getTitle();
    this.description = film.getDescription();
    this.releaseYear = film.getReleaseYear();
    this.language = film.getLanguage();
    this.length = film.getLength();
    this.rating = film.getRating().getValue();
    this.category = film.getCategory();
    this.cast = film.getCast().stream()
        .map(ReducedActorOutput::new)
        .collect(Collectors.toSet());
  }
}
