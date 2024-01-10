package com.auto.companion.servicehistory.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceHistoryDto {

  private String vinCode;
  private String type;
  private LocalDate date;
  private String description;
}
