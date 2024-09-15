package com.harsh.eventmanagement.service;

public interface NotificationService {

    public void sendEmail(String recipientEmail, String subject, String message);

}
