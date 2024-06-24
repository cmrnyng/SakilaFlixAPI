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

import com.cmrn_yng.sakilaflix.services.ActorService;
import com.cmrn_yng.sakilaflix.entities.Actor;
import com.cmrn_yng.sakilaflix.input.ActorInput;
import com.cmrn_yng.sakilaflix.input.ValidationGroup.Create;
import com.cmrn_yng.sakilaflix.output.ActorDetailsOutput;

@RestController
@RequestMapping("/actors")
public class ActorController {

  @Autowired
  private ActorService actorService;

  @GetMapping("/{id}")
  public ActorDetailsOutput getActorById(@PathVariable Short id) {
    return actorService.findById(id);
  }

  @GetMapping
  public List<ActorDetailsOutput> getActors(@RequestParam(required = false) Optional<String> name) {
    return name.map(value -> actorService.findByName(value))
        .orElseGet(() -> actorService.findAll())
        .stream()
        .map(ActorDetailsOutput::new).toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ActorDetailsOutput createActor(@Validated(Create.class) @RequestBody ActorInput data) {
    Actor created = actorService.createActor(data);
    return new ActorDetailsOutput(created);
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
