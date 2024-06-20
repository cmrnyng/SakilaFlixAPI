package com.cmrn_yng.sakilaflix.inputs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.cmrn_yng.sakilaflix.inputs.ValidationGroup.Create;

@Data
public class FilmInput {
  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 128)
  private String title;

  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 10000)
  private String description;

  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 128)
  private java.time.LocalDate releaseYear;

  @NotNull(groups = { Create.class })
  @Min(1)
  private Byte languageId;

  @NotNull(groups = { Create.class })
  @Min(1)
  private Short length;

  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 5)
  private String rating;
}
