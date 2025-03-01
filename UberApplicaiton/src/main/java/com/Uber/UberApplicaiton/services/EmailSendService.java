package com.Uber.UberApplicaiton.services;

public interface EmailSendService {


    void sendEmail(String toEmail, String subject, String body);

    void sendEmail(String toEmail[], String subject, String body);
}
