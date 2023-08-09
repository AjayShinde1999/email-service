package com.emailservice.service;

import javax.mail.MessagingException;

public interface EmailService {

    public void sendRegistrationEmail(String toEmail, String subject, String text) throws MessagingException;
}
