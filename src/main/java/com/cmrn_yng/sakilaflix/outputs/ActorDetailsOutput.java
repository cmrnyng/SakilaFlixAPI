package com.cmrn_yng.sakilaflix.outputs;

import java.util.Set;

import com.cmrn_yng.sakilaflix.entities.Actor;
import com.cmrn_yng.sakilaflix.entities.Film;

import lombok.Getter;

@Getter
public class ActorDetailsOutput {
  private final Short id;
  private final String firstName;
  private final String lastName;
  private final Set<Film> starredFilms;

  public ActorDetailsOutput(Actor actor) {
    this.id = actor.getId();
    this.firstName = actor.getFirstName();
    this.lastName = actor.getLastName();
    this.starredFilms = actor.getStarredFilms();
  }
}
