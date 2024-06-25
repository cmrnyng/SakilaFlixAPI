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

import com.cmrn_yng.sakilaflix.services.ActorService;
import com.cmrn_yng.sakilaflix.input.ActorInput;
import com.cmrn_yng.sakilaflix.input.ValidationGroup.Create;
import com.cmrn_yng.sakilaflix.output.ActorDetailsOutput;
import com.cmrn_yng.sakilaflix.output.FinalActorOutput;

@RestController
@RequestMapping("/actors")
public class ActorController {

  @Autowired
  private ActorService actorService;

  @GetMapping("/{id}")
  public ActorDetailsOutput getActorById(@PathVariable Short id) {
    return new ActorDetailsOutput(actorService.findById(id));
  }

  @GetMapping
  public FinalActorOutput getActors(
      @RequestParam(required = false) Optional<String> name,
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "25") int pageSize,
      @RequestParam(required = false) Optional<String> sort) {
    Sort sortOrder = sort.map((val) -> {
      String[] sortParams = val.split(",");
      Sort.Direction direction = Sort.Direction.fromString(sortParams[1]);
      return Sort.by(direction, sortParams[0]);
    }).orElse(Sort.unsorted());
    Pageable pageable = PageRequest.of(pageNo, pageSize, sortOrder);
    return actorService.findAll(name, pageable);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ActorDetailsOutput createActor(@Validated(Create.class) @RequestBody ActorInput data) {
    return new ActorDetailsOutput(actorService.createActor(data));
  }

  @PutMapping("/update/{id}")
  public String updateActor(@PathVariable Short id, @RequestBody ActorInput data) {
    return actorService.updateActor(id, data);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteActor(@PathVariable Short id) {
    return actorService.deleteActor(id);
  }
}
