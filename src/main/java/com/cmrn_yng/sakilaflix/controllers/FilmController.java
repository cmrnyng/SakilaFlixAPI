package com.cmrn_yng.sakilaflix.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.cmrn_yng.sakilaflix.output.FinalFilmOutput;
import com.cmrn_yng.sakilaflix.services.FilmService;

@RestController
@RequestMapping("/movies")
public class FilmController {

  @Autowired
  private FilmService filmService;

  @GetMapping
  public FinalFilmOutput getFilms(
      @RequestParam(required = false) Optional<String> title,
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "25") int pageSize,
      @RequestParam(required = false) Optional<String> sort,
      @RequestParam(required = false) Optional<String> category,
      @RequestParam(required = false) Optional<String> language) {
    Sort sortOrder = sort.map((val) -> {
      String[] sortParams = val.split(",");
      Sort.Direction direction = Sort.Direction.fromString(sortParams[1]);
      return Sort.by(direction, sortParams[0]);
    }).orElse(Sort.unsorted());
    Pageable pageable = PageRequest.of(pageNo, pageSize, sortOrder);
    return filmService.findAll(title, category, language, pageable);
  }

  @GetMapping("/{id}")
  public FilmDetailsOutput getFilmById(@PathVariable Short id) {
    return new FilmDetailsOutput(filmService.findById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FilmDetailsOutput createFilm(@Validated(Create.class) @RequestBody FilmInput data) {
    return new FilmDetailsOutput(filmService.createFilm(data));
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
