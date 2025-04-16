package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.BloodBank;
import org.example.blood_donation_api.Entity.BloodTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepo extends JpaRepository<BloodBank, Integer> {
    Optional<BloodBank> findFirstByBloodType(BloodTypes bloodType);
    @Query("SELECT SUM(b.points) FROM BloodBank b")
    double getTotalBloodStock();

    List<BloodBank> findByBloodType(BloodTypes bloodType);


}
