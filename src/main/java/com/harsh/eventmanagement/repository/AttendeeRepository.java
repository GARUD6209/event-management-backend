package com.harsh.eventmanagement.repository;

import com.harsh.eventmanagement.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Set<Attendee> findByEventId(Long eventId);
}
