package com.cmrn_yng.sakilaflix.outputs;

import com.cmrn_yng.sakilaflix.entities.Actor;

import lombok.Getter;

@Getter
public class ActorDetailsOutput {
  private final Short id;
  private final String firstName;
  private final String lastName;

  public ActorDetailsOutput(Actor actor) {
    this.id = actor.getId();
    this.firstName = actor.getFirstName();
    this.lastName = actor.getLastName();
  }
}
