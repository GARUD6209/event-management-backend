package com.harsh.eventmanagement.controller;

import com.harsh.eventmanagement.model.Attendee;
import com.harsh.eventmanagement.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping("/event/{eventId}/rsvp")
    public Attendee rsvp(@PathVariable Long eventId, @RequestBody Attendee attendee) {
        return attendeeService.rsvpForEvent(eventId, attendee);
    }

    @GetMapping("/event/{eventId}")
    public Set<Attendee> getAttendees(@PathVariable Long eventId) {
        return attendeeService.getAttendeesForEvent(eventId);
    }
}

