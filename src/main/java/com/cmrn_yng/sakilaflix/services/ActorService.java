package com.cmrn_yng.sakilaflix.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cmrn_yng.sakilaflix.entities.Actor;
import com.cmrn_yng.sakilaflix.input.ActorInput;
import com.cmrn_yng.sakilaflix.output.ActorDetailsOutput;
import com.cmrn_yng.sakilaflix.repos.ActorRepo;

@Service
public class ActorService {

  @Autowired
  private ActorRepo actorRepo;

  public Actor createActor(ActorInput data) {
    Actor actor = new Actor();
    actor.setFirstName(data.getFirstName());
    actor.setLastName(data.getLastName());

    Actor actorSaved = actorRepo.save(actor);
    return actorRepo.findById(actorSaved.getId()).orElseThrow();
  }

  public List<Actor> findAll() {
    return actorRepo.findAll();
  }

  public List<Actor> findByName(String name) {
    return actorRepo.findByFullNameContainingIgnoreCase(name);
  }

  public ActorDetailsOutput findById(Short id) {
    return actorRepo.findById(id).map(ActorDetailsOutput::new).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
  }

  public String updateActor(Short id, ActorInput data) {
    Actor updatedActor = actorRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
    updatedActor.setFirstName(data.getFirstName());
    updatedActor.setLastName(data.getLastName());
    actorRepo.save(updatedActor);
    return "Actor details updated.";
  }

  public String deleteActor(Short id) {
    Actor deletedActor = actorRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
    actorRepo.delete(deletedActor);
    return "Actor with id " + id + " removed.";
  }
}
