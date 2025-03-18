package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodBankRepo extends JpaRepository<BloodBank, Integer> {
}
