package org.example.blood_donation_api;

import org.example.blood_donation_api.Repo.DonationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloodDonationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodDonationApiApplication.class, args);
    }

}
