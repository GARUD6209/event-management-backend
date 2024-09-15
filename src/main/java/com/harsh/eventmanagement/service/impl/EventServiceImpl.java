package com.harsh.eventmanagement.service.impl;

import com.harsh.eventmanagement.model.Event;
import com.harsh.eventmanagement.repository.AttendeeRepository;
import com.harsh.eventmanagement.repository.EventRepository;
import com.harsh.eventmanagement.service.EventService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    // Constructor-based dependency injection
    public EventServiceImpl(EventRepository eventRepository, EntityManager entityManager, AttendeeRepository attendeeRepository) {
        this.eventRepository = eventRepository;
        this.entityManager = entityManager;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        Event managedEvent = entityManager.merge(event);
        entityManager.flush();  // Ensure the entity is persisted and the ID is generated

        System.out.println("Generated Event ID: " + managedEvent.getId());  // Now the ID should be available

        if (managedEvent.getAttendees() != null && !managedEvent.getAttendees().isEmpty()) {
            attendeeRepository.saveAll(managedEvent.getAttendees());
        }

        System.out.println("Generated Event : " + managedEvent);
        return managedEvent;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}