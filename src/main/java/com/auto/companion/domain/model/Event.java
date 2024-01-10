package com.auto.companion.domain.model;

import com.auto.companion.domain.model.base.AbstractIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event  extends AbstractIdentifiable {

    private String type;
    private String description;
    private LocalDate date;
    private String vinCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
