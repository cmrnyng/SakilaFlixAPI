package com.cmrn_yng.sakilaflix.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cmrn_yng.sakilaflix.repos.ActorRepo;

import com.cmrn_yng.sakilaflix.entities.Actor;
import com.cmrn_yng.sakilaflix.inputs.ActorInput;
import com.cmrn_yng.sakilaflix.inputs.ValidationGroup.Create;
import com.cmrn_yng.sakilaflix.outputs.ActorDetailsOutput;

@RestController
@RequestMapping("/actors")
public class ActorController {

  @Autowired
  private ActorRepo actorRepo;

  @GetMapping
  public List<ActorDetailsOutput> getActors() {
    return actorRepo.findAll().stream().map(ActorDetailsOutput::new).toList();
  }

  @GetMapping("/{id}")
  public ActorDetailsOutput getActorById(@PathVariable Short id) {
    return actorRepo.findById(id).map(ActorDetailsOutput::new).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ActorDetailsOutput createActor(@Validated(Create.class) @RequestBody ActorInput data) {
    Actor actor = new Actor();
    actor.setFirstName(data.getFirstName());
    actor.setLastName(data.getLastName());

    Actor created = actorRepo.save(actor);
    return new ActorDetailsOutput(created);
  }
}
