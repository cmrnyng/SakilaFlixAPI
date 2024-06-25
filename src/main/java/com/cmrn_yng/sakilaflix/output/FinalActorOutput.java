package com.cmrn_yng.sakilaflix.output;

import java.util.List;

import lombok.Data;

@Data
public class FinalActorOutput {
  private List<ActorDetailsOutput> content;
  private int pageNo;
  private int pageSize;
  private long totalElements;
  private int totalPages;
  private boolean last;
}
