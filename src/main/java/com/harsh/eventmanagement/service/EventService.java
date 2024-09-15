package com.harsh.eventmanagement.service;

import com.harsh.eventmanagement.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    void deleteEvent(Long id);
}
