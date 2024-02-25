package com.dveljkovic.elearning.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
