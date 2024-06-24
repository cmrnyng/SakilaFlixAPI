package com.cmrn_yng.sakilaflix.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cmrn_yng.sakilaflix.entities.Film;
import com.cmrn_yng.sakilaflix.entities.Language;
import com.cmrn_yng.sakilaflix.inputs.FilmInput;
import com.cmrn_yng.sakilaflix.inputs.ValidationGroup.Create;
import com.cmrn_yng.sakilaflix.outputs.FilmDetailsOutput;
import com.cmrn_yng.sakilaflix.repos.FilmRepo;
import com.cmrn_yng.sakilaflix.repos.LanguageRepo;

@RestController
@RequestMapping("/movies")
public class FilmController {

  @Autowired
  private FilmRepo filmRepo;

  @Autowired
  private LanguageRepo languageRepo;

  @GetMapping
  public List<Film> getFilms() {
    return filmRepo.findAll();
  }

  @GetMapping("/{id}")
  public FilmDetailsOutput getFilmById(@PathVariable Short id) {
    return filmRepo.findById(id).map(FilmDetailsOutput::new).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such movie with id %d.", id)));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FilmDetailsOutput createFilm(@Validated(Create.class) @RequestBody FilmInput data) {
    Film film = new Film();
    film.setTitle(data.getTitle());
    film.setDescription(data.getDescription());
    film.setReleaseYear(data.getReleaseYear());
    Language language = languageRepo.findById(data.getLanguageId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    film.setLanguage(language);
    film.setLength(data.getLength());
    film.setRating(data.getRating());

    Film created = filmRepo.save(film);
    return new FilmDetailsOutput(created);
  }

  @PutMapping("/update/{id}")
  public String updateFilm(@PathVariable Short id, @RequestBody FilmInput data) {
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
    filmRepo.save(updatedFilm);

    return "Movie details updated.";
  }

  @DeleteMapping("/delete/{id}")
  public String deleteFilm(@PathVariable Short id) {
    Film deletedFilm = filmRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such movie with id %d.", id)));
    filmRepo.delete(deletedFilm);
    return "Movie with id " + id + " deleted.";
  }
}
