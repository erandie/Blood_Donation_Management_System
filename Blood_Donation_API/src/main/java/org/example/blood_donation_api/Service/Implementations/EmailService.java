package org.example.blood_donation_api.Service.Implementations;

import org.example.blood_donation_api.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailDTO dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hansierandi7@gmail.com");
        message.setTo(dto.getEmail());
        message.setSubject(dto.getSubject());
        message.setText("Name: " + dto.getName() + "\n\nMessage: " + dto.getMessage());

        javaMailSender.send(message);
    }


}
