package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DonationRepo extends JpaRepository<Donation, Integer> {
    long count();
    Optional<Donation> findByDonationId(Integer donationId);
    @Query("SELECT count(d) FROM Donation d WHERE d.donor.donorId = :donorId")
    Integer getDonationCountByUser(@Param("donorId") Integer donorId);

}
