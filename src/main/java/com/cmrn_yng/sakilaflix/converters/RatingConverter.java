package com.cmrn_yng.sakilaflix.converters;

import com.cmrn_yng.sakilaflix.enums.Rating;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

  @Override
  public String convertToDatabaseColumn(Rating rating) {
    if (rating == null)
      return null;
    return rating.getValue();
  }

  @Override
  public Rating convertToEntityAttribute(String value) {
    if (value == null)
      return null;
    return Stream.of(Rating.values()).filter(v -> v.getValue().equals(value)).findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
