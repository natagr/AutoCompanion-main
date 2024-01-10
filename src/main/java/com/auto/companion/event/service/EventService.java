package com.auto.companion.event.service;

import com.auto.companion.domain.model.Event;
import com.auto.companion.domain.model.User;
import com.auto.companion.domain.model.mapper.EventMapper;
import com.auto.companion.domain.repository.EventRepository;
import com.auto.companion.domain.repository.UserRepository;
import com.auto.companion.event.model.EventDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;


    @Transactional
    public void createEvent(Long userId, EventDto eventDto) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Event event = new Event();
        event.setType(eventDto.getType());
        event.setDescription(eventDto.getDescription());
        event.setDate(LocalDate.now());
        event.setUser(user);
        event.setVinCode(event.getVinCode());
        eventRepository.save(event);
    }


    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found")); // Краще використовувати спеціалізоване виключення
    }

    @Transactional(readOnly = true)
    public Event getEventByIdAndUserId(Long eventId, Long userId) {
        return eventRepository.findByIdAndUserId(eventId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }

    @Transactional
    public void updateEvent(Long id, EventDto eventDto) {
        Event event = getEventById(id);
        eventMapper.updateCarInfo(eventDto, event);
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
//    public List<EventDto> getEventsByUserId( Long userId, Pageable pageable) {
//        return eventRepository.findAllByIdAndUserId(userId,pageable)
//                .map(eventMapper::toDto)
//                .getContent();
//    }

    public List<EventDto> getEventsByMonthAndYear(Long userId, Integer month, Integer year) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());
        return eventRepository.findByMonthAndYear(startOfMonth, endOfMonth,userId)
                .stream()
                .map(eventMapper::toDto)
                .toList();

    }
}
