package com.harsh.eventmanagement.controller;

import com.harsh.eventmanagement.model.Attendee;
import com.harsh.eventmanagement.model.Event;
import com.harsh.eventmanagement.service.AttendeeService;
import com.harsh.eventmanagement.service.EventService;
import com.harsh.eventmanagement.service.NotificationService;
import com.harsh.eventmanagement.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private AttendeeService attendeeService;
    @Autowired
    private NotificationServiceImpl notificationService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {

        return eventService.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/noti")
    public String xx() {
        notificationService.sendDailyReminders();
        return   " : ";
    }
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventService.getEventById(id);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            Set<Attendee> attendeeSet = attendeeService.getAttendeesForEvent(event.getId());



            event.setAttendees(attendeeSet);  // Convert List to Set
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
