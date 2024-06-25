package com.cmrn_yng.sakilaflix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cmrn_yng.sakilaflix.entities.Actor;
import com.cmrn_yng.sakilaflix.input.ActorInput;
import com.cmrn_yng.sakilaflix.output.ActorDetailsOutput;
import com.cmrn_yng.sakilaflix.output.FinalActorOutput;
import com.cmrn_yng.sakilaflix.repos.ActorRepo;
import com.cmrn_yng.sakilaflix.repos.ActorSpecs;

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

  public FinalActorOutput findAll(Optional<String> name, Pageable pageable) {
    Specification<Actor> specs = Specification.where(null);

    if (name.isPresent())
      specs = specs.and(ActorSpecs.nameContains(name.get()));

    Page<ActorDetailsOutput> actors = actorRepo.findAll(specs, pageable).map(ActorDetailsOutput::new);
    List<ActorDetailsOutput> content = actors.getContent();

    FinalActorOutput actorResponse = new FinalActorOutput();
    actorResponse.setContent(content);
    actorResponse.setPageNo(actors.getNumber());
    actorResponse.setPageSize(actors.getSize());
    actorResponse.setTotalElements(actors.getTotalElements());
    actorResponse.setTotalPages(actors.getTotalPages());
    actorResponse.setLast(actors.isLast());

    return actorResponse;
  }

  public Actor findById(Short id) {
    return actorRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
  }

  public Actor updateActor(Short id, ActorInput data) {
    Actor updatedActor = actorRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
    updatedActor.setFirstName(data.getFirstName());
    updatedActor.setLastName(data.getLastName());
    return actorRepo.save(updatedActor);
  }

  public String deleteActor(Short id) {
    Actor deletedActor = actorRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No such actor with id %d.", id)));
    actorRepo.delete(deletedActor);
    return "Actor with id " + id + " removed.";
  }
}
