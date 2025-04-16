package org.example.blood_donation_api.Repo;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.blood_donation_api.Entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionistRepo extends JpaRepository<Receptionist, Integer> {

    List<Receptionist> findByNameContainingIgnoreCase(String name);

}
