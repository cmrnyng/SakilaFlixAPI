package com.cmrn_yng.sakilaflix.input;

import com.cmrn_yng.sakilaflix.input.ValidationGroup.Create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActorInput {
  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 45)
  private String firstName;

  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 45)
  private String lastName;
}
