package com.cmrn_yng.sakilaflix.services;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cmrn_yng.sakilaflix.entities.Actor;
import com.cmrn_yng.sakilaflix.entities.Category;
import com.cmrn_yng.sakilaflix.entities.Film;
import com.cmrn_yng.sakilaflix.entities.Language;
import com.cmrn_yng.sakilaflix.input.FilmInput;
import com.cmrn_yng.sakilaflix.output.FilmDetailsOutput;
import com.cmrn_yng.sakilaflix.output.FinalFilmOutput;
import com.cmrn_yng.sakilaflix.repos.ActorRepo;
import com.cmrn_yng.sakilaflix.repos.CategoryRepo;
import com.cmrn_yng.sakilaflix.repos.FilmRepo;
import com.cmrn_yng.sakilaflix.repos.FilmSpecs;
import com.cmrn_yng.sakilaflix.repos.LanguageRepo;

@Service
public class FilmService {

  @Autowired
  private FilmRepo filmRepo;

  @Autowired
  private LanguageRepo languageRepo;

  @Autowired
  private ActorRepo actorRepo;

  @Autowired
  private CategoryRepo categoryRepo;

  public Film createFilm(FilmInput data) {
    Film film = new Film();
    film.setTitle(data.getTitle());
    film.setDescription(data.getDescription());
    film.setReleaseYear(data.getReleaseYear());
    Language language = languageRepo.findById(data.getLanguageId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    film.setLanguage(language);
    film.setLength(data.getLength());
    film.setRating(data.getRating());
    Category category = categoryRepo.findById(data.getCategoryId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    film.setCategory(category);
    Set<Actor> cast = data.getCast().stream()
        .map(actorId -> actorRepo.findById(actorId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)))
        .collect(Collectors.toSet());
    film.setCast(cast);

    return filmRepo.save(film);
  }

  public FinalFilmOutput findAll(Optional<String> title, Optional<String> category, Optional<String> language,
      Pageable pageable) {
    Specification<Film> specs = Specification.where(null);

    if (title.isPresent())
      specs = specs.and(FilmSpecs.titleContains(title.get()));
    if (category.isPresent())
      specs = specs.and(FilmSpecs.categoryIs(category.get()));
    if (language.isPresent())
      specs = specs.and(FilmSpecs.languageIs(language.get()));

    Page<FilmDetailsOutput> films = filmRepo.findAll(specs, pageable).map(FilmDetailsOutput::new);
    List<FilmDetailsOutput> content = films.getContent();

    FinalFilmOutput filmResponse = new FinalFilmOutput();
    filmResponse.setContent(content);
    filmResponse.setPageNo(films.getNumber());
    filmResponse.setPageSize(films.getSize());
    filmResponse.setTotalElements(films.getTotalElements());
    filmResponse.setTotalPages(films.getTotalPages());
    filmResponse.setLast(films.isLast());

    return filmResponse;
  }

  public Film findById(Short id) {
    return filmRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such movie with id %d.", id)));
  }

  public Film updateFilm(Short id, FilmInput data) {
    Film updatedFilm = filmRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such movie with id %d.", id)));
    updatedFilm.setTitle(data.getTitle());
    updatedFilm.setDescription(data.getDescription());
    updatedFilm.setReleaseYear(data.getReleaseYear());
    Language language = languageRepo.findById(data.getLanguageId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    updatedFilm.setLanguage(language);
    updatedFilm.setLength(data.getLength());
    updatedFilm.setRating(data.getRating());
    Category category = categoryRepo.findById(data.getCategoryId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    updatedFilm.setCategory(category);
    Set<Actor> cast = data.getCast().stream()
        .map(actorId -> actorRepo.findById(actorId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)))
        .collect(Collectors.toSet());
    updatedFilm.setCast(cast);

    return filmRepo.save(updatedFilm);
  }

  public String deleteFilm(Short id) {
    Film deletedFilm = filmRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such movie with id %d.", id)));
    filmRepo.delete(deletedFilm);
    return "Movie with id " + id + " deleted.";
  }
}
