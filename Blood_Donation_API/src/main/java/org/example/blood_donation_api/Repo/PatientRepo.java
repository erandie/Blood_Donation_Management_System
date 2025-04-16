package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

    List<Patient> findByNameContainingIgnoreCase(String name);

}
