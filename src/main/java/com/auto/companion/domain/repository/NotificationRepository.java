package com.auto.companion.domain.repository;

import com.auto.companion.domain.model.Notification;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

  List<Notification> findByReadTrue();

  List<Notification> findByUserId(Long userId);
}
