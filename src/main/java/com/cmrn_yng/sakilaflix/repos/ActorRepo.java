package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cmrn_yng.sakilaflix.entities.Actor;

public interface ActorRepo extends JpaRepository<Actor, Short>, JpaSpecificationExecutor<Actor> {
}
