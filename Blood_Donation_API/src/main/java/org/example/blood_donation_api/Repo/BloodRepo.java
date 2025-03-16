package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Blood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRepo extends JpaRepository<Blood, Integer> {

}
