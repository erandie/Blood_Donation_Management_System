package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Funds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundsRepo extends JpaRepository<Funds, Integer> {

    List<Funds> findByDescriptionContainingIgnoreCase(String description);


}
