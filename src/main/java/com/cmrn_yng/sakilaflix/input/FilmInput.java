package com.cmrn_yng.sakilaflix.input;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.cmrn_yng.sakilaflix.enums.Rating;
import com.cmrn_yng.sakilaflix.input.ValidationGroup.Create;

@Data
public class FilmInput {
  @NotNull(groups = { Create.class })
  @Size(min = 1, max = 128)
  private String title;

  @Size(max = 10000)
  private String description;

  @Min(1901)
  @Max(9999)
  private Integer releaseYear;

  @NotNull(groups = { Create.class })
  @Min(1)
  private Byte languageId;

  @NotNull(groups = { Create.class })
  @Min(1)
  private Short length;

  private Rating rating;

  @NotNull(groups = { Create.class })
  @Min(1)
  private Byte categoryId;

  private List<Short> cast = new ArrayList<>();
}
