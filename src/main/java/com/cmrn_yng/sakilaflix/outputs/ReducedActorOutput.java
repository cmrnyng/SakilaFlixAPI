package com.cmrn_yng.sakilaflix.outputs;

import lombok.Getter;

@Getter
public class ReducedActorOutput {
  private final Short id;
  private final String firstName;
  private final String lastName;

  public ReducedActorOutput(Short id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
