package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.services.EmailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class EmailSendServiceImple implements EmailSendService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        try {


            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e){
            log.info("Cannot send Email: "+e.getMessage());
        }
    }

    @Override
    public void sendEmail(String[] toEmail, String subject, String body) {
        try {


            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e){
            log.info("Cannot send Email: "+e.getMessage());
        }
    }
}
