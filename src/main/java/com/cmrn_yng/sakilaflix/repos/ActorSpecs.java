package com.cmrn_yng.sakilaflix.repos;

import org.springframework.data.jpa.domain.Specification;

import com.cmrn_yng.sakilaflix.entities.Actor;

public class ActorSpecs {
  public static Specification<Actor> nameContains(String name) {
    return (root, query, builder) -> builder.like(builder.lower(root.get("fullName")), "%" + name.toLowerCase() + "%");
  }
}
