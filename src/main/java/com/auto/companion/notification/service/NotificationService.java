package com.auto.companion.notification.service;

import com.auto.companion.domain.model.Event;
import com.auto.companion.domain.model.Notification;
import com.auto.companion.domain.model.User;
import com.auto.companion.domain.model.mapper.NotificationMapper;
import com.auto.companion.domain.repository.EventRepository;
import com.auto.companion.domain.repository.NotificationRepository;
import com.auto.companion.notification.model.NotificationDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;
  private final EventRepository eventRepository;

  @Transactional(readOnly = true)
  public List<NotificationDto> getAllNotifications(Long userId) {
    return notificationRepository.findByUserId(userId)
            .stream()
            .map(notificationMapper::toDto)
            .toList();
  }

  @Transactional(readOnly = true)
  public Notification getNotificationById(Long id) {
    return notificationRepository.findById(id).orElseThrow(() -> new RuntimeException(
        "Notifications not found"));
  }

  @Transactional
  public void addNotification(NotificationDto notificationDto, User user) {
    Event event = eventRepository.findById(notificationDto.getEventId()).orElseThrow(EntityNotFoundException::new);
    Notification notification = new Notification();
    notification.setUser(user);
    notification.setRead(false);
    notification.setDescription(notificationDto.getDescription());
    notification.setType(notificationDto.getType());
    notification.setEvent(event);
    notificationRepository.save(notification);
  }

  @Transactional
  public void markAsRead(Long id) {
    Optional<Notification> optionalNotification = notificationRepository.findById(id);
    if (optionalNotification.isPresent()) {
      Notification notification = optionalNotification.get();
      notification.setRead(true);
      notificationRepository.save(notification);
    }
  }

  @Transactional
  public void deleteReadNotifications() {
    List<Notification> readNotifications = notificationRepository.findByReadTrue();
    notificationRepository.deleteAll(readNotifications);
  }

  @Transactional
  public void updateNotification(Long id, NotificationDto notificationDto) {
    Notification notification = getNotificationById(id);
    notificationMapper.updateNotificationInfo(notificationDto, notification);
    notificationRepository.save(notification);
  }
}
