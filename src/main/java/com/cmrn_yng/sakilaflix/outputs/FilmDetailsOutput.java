package com.cmrn_yng.sakilaflix.outputs;

import java.util.Set;
import java.util.stream.Collectors;

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
  private final Set<ReducedActorOutput> starredActors;

  public FilmDetailsOutput(Film film) {
    this.id = film.getId();
    this.title = film.getTitle();
    this.description = film.getDescription();
    this.releaseYear = film.getReleaseYear();
    this.language = film.getLanguage();
    this.length = film.getLength();
    this.rating = film.getRating().getValue();
    this.starredActors = film.getStarredActors().stream()
        .map(ReducedActorOutput::new)
        .collect(Collectors.toSet());
  }
}
