package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.EmailService;
import org.example.blood_donation_api.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("send")
    public String sendEmail(@RequestBody EmailDTO emailDTO) {
        try {
            emailService.sendEmail(emailDTO.getTo(), emailDTO.getSubject(), emailDTO.getMessage());
            return "Email Sent Successfully! :)";
        } catch (Exception e) {
            return "Failed to sent email: " + e.getMessage();
        }
    }

}
