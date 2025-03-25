package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepo extends JpaRepository<Donation, Integer> {
}
