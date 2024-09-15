package com.harsh.eventmanagement.repository;

import com.harsh.eventmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    Set<Event> findByEventDateBetween(LocalDateTime now, LocalDateTime oneHourLater);

}

