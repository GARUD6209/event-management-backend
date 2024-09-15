package com.harsh.eventmanagement.service.impl;

import com.harsh.eventmanagement.model.Attendee;
import com.harsh.eventmanagement.model.Event;
import com.harsh.eventmanagement.repository.AttendeeRepository;
import com.harsh.eventmanagement.repository.EventRepository;
import com.harsh.eventmanagement.service.AttendeeService;
import com.harsh.eventmanagement.service.EventService;
import com.harsh.eventmanagement.service.NotificationService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    EventService eventService;

    @Autowired
    AttendeeRepository attendeeRepository;

    @Value("${spring.mail.username}")
    String domainName;


    @Autowired
    private JavaMailSender mailSender;  // Spring Boot mail sender

//     Method to send reminders for events happening today
    @Transactional
    @Scheduled(cron = "0/15 * * * * ?")
//    @Scheduled(cron = "0 0 6 * * ?")                                   // Run every day at 6 AM
    public void sendDailyReminders() {
        System.out.println("invoked" );
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        // Fetch events happening today
        Set<Event> todaysEvents = eventRepository.findByEventDateBetween(startOfDay, endOfDay);

        

        System.out.println("NotificationServiceImpl | todaysEvents : "+todaysEvents);

        for (Event event : todaysEvents) {
            Optional<Event> optionalEvent = eventRepository.findById(event.getId());
            if (optionalEvent.isPresent()) {
                try {
                Event eventbyID = optionalEvent.get();
                Set<Attendee> attendeeSet = attendeeRepository.findByEventId(eventbyID.getId());

                eventbyID.setAttendees(attendeeSet);  // Convert List to Set


                    sendReminderEmails(eventbyID);
                } catch (Exception e) {
                    System.err.println("Failed to send email to  : " + e.getMessage());

                }
            }
        }
    }

    private void sendReminderEmails(Event event) {
        String subject = "Reminder: Upcoming Event Today - " + event.getTitle();
        String message = "Dear Attendee,\n\nJust a reminder that the event '" + event.getTitle() +
                "' will take place at " + event.getLocation() + " on " + event.getEventDate() + ".\n\nThank you!";

        for (Attendee attendee : event.getAttendees()) {
            String recipientEmail = attendee.getEmail();

            try {
                sendEmail(recipientEmail, subject, message);
            } catch (Exception e) {
                System.err.println("Failed to send email to " + recipientEmail + ": " + e.getMessage());

            }
        }
    }

    @Async
    public void sendEmail(String recipientEmail, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(domainName);
        email.setTo(recipientEmail);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
