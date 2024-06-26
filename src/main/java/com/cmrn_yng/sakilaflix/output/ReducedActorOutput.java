package com.cmrn_yng.sakilaflix.output;

import com.cmrn_yng.sakilaflix.entities.Actor;

import lombok.Getter;

@Getter
public class ReducedActorOutput {
  private final Short id;
  private final String firstName;
  private final String lastName;

  public ReducedActorOutput(Actor actor) {
    this.id = actor.getId();
    this.firstName = actor.getFirstName();
    this.lastName = actor.getLastName();
  }
}