package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
