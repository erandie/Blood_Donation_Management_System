package org.example.blood_donation_api.Controller;

import ch.qos.logback.core.model.Model;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin
public class DashboardController {

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
      return "Admin Dashboard content!";
    }

    @GetMapping("user")
    @PreAuthorize("hasRole('USER')")
    public String userDashboard() {
        return "User Dashboard content!";
    }


}
