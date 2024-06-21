package com.cmrn_yng.sakilaflix.entities;

import com.cmrn_yng.sakilaflix.enums.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "film_id")
  private Short id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "release_year")
  private Integer releaseYear;

  @Column(name = "language_id", nullable = false)
  private Byte languageId;

  @Column(name = "length")
  private Short length;

  @Column(name = "rating")
  private Rating rating;
}
