package com.cmrn_yng.sakilaflix.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "actor_id")
  private Short id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Formula("concat(first_name, ' ', last_name)")
  private String fullName;

  @ManyToMany(mappedBy = "cast")
  private Set<Film> starredFilms = new HashSet<>();

  public String getFullName() {
    return firstName + " " + lastName;
  }
}
