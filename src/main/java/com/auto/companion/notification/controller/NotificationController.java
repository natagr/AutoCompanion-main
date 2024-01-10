package com.auto.companion.notification.controller;

import com.auto.companion.domain.model.Notification;
import com.auto.companion.domain.model.User;
import com.auto.companion.notification.model.NotificationDto;
import com.auto.companion.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<NotificationDto> getAllNotifications(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    return notificationService.getAllNotifications(user.getId());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Notification getNotificationById(@PathVariable Long id) {
    return notificationService.getNotificationById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addNotification(@RequestBody NotificationDto notificationDto, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    notificationService.addNotification(notificationDto,user);
  }

  @PutMapping("/{id}/markAsRead")
  @ResponseStatus(HttpStatus.OK)
  public void markAsRead(@PathVariable Long id) {
    notificationService.markAsRead(id);
  }

  @DeleteMapping("/delete")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteReadNotifications() {
    notificationService.deleteReadNotifications();
  }

//  @PutMapping("/{id}")
//  @ResponseStatus(HttpStatus.ACCEPTED)
//  public void updateNotification(@PathVariable Long id, @RequestBody NotificationDto notificationDto) {
//    notificationService.updateNotification(id, notificationDto);
//  }
}
