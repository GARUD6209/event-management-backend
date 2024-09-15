package com.harsh.eventmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToOne
    @JsonBackReference  // Prevent recursion by ignoring this side during serialization
    @JoinColumn(name = "event_id")  // Foreign key to Event entity
    private Event event;

    @Override
    public String toString() {
        return "Attendee{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
