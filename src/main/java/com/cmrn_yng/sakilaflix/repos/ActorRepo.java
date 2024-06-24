package com.cmrn_yng.sakilaflix.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmrn_yng.sakilaflix.entities.Actor;

public interface ActorRepo extends JpaRepository<Actor, Short> {
  List<Actor> findByFullNameContainingIgnoreCase(String name);
}
