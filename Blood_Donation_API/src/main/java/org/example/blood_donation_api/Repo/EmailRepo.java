package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepo extends JpaRepository<Email, Integer> {

}
