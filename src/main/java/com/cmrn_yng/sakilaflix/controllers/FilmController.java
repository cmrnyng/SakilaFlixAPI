package com.cmrn_yng.sakilaflix.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cmrn_yng.sakilaflix.input.FilmInput;
import com.cmrn_yng.sakilaflix.input.ValidationGroup.Create;
import com.cmrn_yng.sakilaflix.output.FilmDetailsOutput;
import com.cmrn_yng.sakilaflix.services.FilmService;

@RestController
@RequestMapping("/movies")
public class FilmController {

  @Autowired
  private FilmService filmService;

  @GetMapping
  public List<FilmDetailsOutput> getFilms(@RequestParam(required = false) Optional<String> title) {
    return title.map(value -> filmService.findByTitle(value))
        .orElseGet(() -> filmService.findAll())
        .stream()
        .map(FilmDetailsOutput::new).toList();
  }

  @GetMapping("/{id}")
  public FilmDetailsOutput getFilmById(@PathVariable Short id) {
    return filmService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FilmDetailsOutput createFilm(@Validated(Create.class) @RequestBody FilmInput data) {
    return filmService.createFilm(data);
  }

  @PutMapping("/update/{id}")
  public String updateFilm(@PathVariable Short id, @RequestBody FilmInput data) {
    return filmService.updateFilm(id, data);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteFilm(@PathVariable Short id) {
    return filmService.deleteFilm(id);
  }
}
