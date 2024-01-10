package com.auto.companion.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "service_history")
public class ServiceHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String vinCode;
  private String type;
  private LocalDate date;
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
