package com.auto.companion.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {
  private String description;
  private String type;
  private Long eventId;
}
