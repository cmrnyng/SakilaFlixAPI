package com.cmrn_yng.sakilaflix.entities;

import java.util.Set;

import com.cmrn_yng.sakilaflix.enums.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne
  @JoinColumn(name = "language_id", nullable = false)
  private Language language;

  @Column(name = "length")
  private Short length;

  @Column(name = "rating")
  private Rating rating;

  @ManyToMany
  @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
  Set<Actor> starredActors;
}
