package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cmrn_yng.sakilaflix.entities.Actor;

public interface ActorRepo extends JpaRepository<Actor, Short> {
  Page<Actor> findByFullNameContainingIgnoreCase(String name, Pageable pageable);
}
