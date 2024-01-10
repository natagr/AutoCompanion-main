package com.auto.companion.domain.model.mapper;


import com.auto.companion.domain.model.Notification;
import com.auto.companion.event.model.EventDto;
import com.auto.companion.notification.model.NotificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

  Notification toEntity(NotificationDto notificationDto);

  @Mapping(source = "event.id", target = "eventId")
  NotificationDto toDto(Notification notification);

  void updateNotificationInfo(NotificationDto dto, @MappingTarget Notification notification);
}
