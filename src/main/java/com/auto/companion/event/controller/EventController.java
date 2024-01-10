package com.auto.companion.event.controller;

import com.auto.companion.domain.model.User;
import com.auto.companion.event.model.EventDto;
import com.auto.companion.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody EventDto eventDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        eventService.createEvent(user.getId(), eventDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getAllEventsByIdAndDate(@RequestParam("month") int month,
                                                  @RequestParam("year") int year,
                                                  Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return eventService.getEventsByMonthAndYear(user.getId(),month, year);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
         eventService.deleteEvent(id);
    }
}
