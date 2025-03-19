package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloodBankRepo extends JpaRepository<BloodBank, Integer> {
    @Query("SELECT b FROM BloodBank b JOIN FETCH b.employees JOIN FETCH b.receptionist")
    List<BloodBank> findAllWithEmployeesAndReceptionist();

}
