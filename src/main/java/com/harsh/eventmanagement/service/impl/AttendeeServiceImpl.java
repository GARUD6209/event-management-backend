package com.harsh.eventmanagement.service.impl;

import com.harsh.eventmanagement.model.Attendee;
import com.harsh.eventmanagement.model.Event;
import com.harsh.eventmanagement.repository.AttendeeRepository;
import com.harsh.eventmanagement.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Override
    public Attendee rsvpForEvent(Long eventId, Attendee attendee) {
        Event event = new Event();
        event.setId(eventId);

        return attendeeRepository.save(attendee);
    }

    @Override
    public Set<Attendee> getAttendeesForEvent(Long eventId) {
        return attendeeRepository.findByEventId(eventId);
    }
}
