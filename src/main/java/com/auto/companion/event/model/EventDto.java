package com.auto.companion.event.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {

    private Long id;
    private String type;
    private String description;
    private LocalDate date;
    private String vinCode;
    private Long userId;
}
