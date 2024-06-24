package com.cmrn_yng.sakilaflix.outputs;

import java.util.Set;
import java.util.stream.Collectors;

import com.cmrn_yng.sakilaflix.entities.Actor;

import lombok.Getter;

@Getter
public class ActorDetailsOutput {
  private final Short id;
  private final String firstName;
  private final String lastName;
  private final Set<ReducedFilmOutput> starredFilms;

  public ActorDetailsOutput(Actor actor) {
    this.id = actor.getId();
    this.firstName = actor.getFirstName();
    this.lastName = actor.getLastName();
    this.starredFilms = actor.getStarredFilms().stream()
        .map(ReducedFilmOutput::new)
        .collect(Collectors.toSet());
  }
}
