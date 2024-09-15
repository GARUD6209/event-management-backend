package com.harsh.eventmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "event_id")
    @JsonManagedReference  // Allow forward serialization of attendees
    private Set<Attendee> attendees;

    @Override
    public String toString() {
        return "Event{id=" + id + ", title='" + title + "', description='" + description + "', eventDate=" + eventDate + ", location='" + location + "'}";
    }
}
