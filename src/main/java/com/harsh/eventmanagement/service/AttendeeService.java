package com.harsh.eventmanagement.service;

import com.harsh.eventmanagement.model.Attendee;

import java.util.List;
import java.util.Set;

public interface AttendeeService {

    Attendee rsvpForEvent(Long eventId, Attendee attendee);

    Set<Attendee> getAttendeesForEvent(Long eventId);
}
