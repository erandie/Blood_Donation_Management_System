package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends JpaRepository<Donor, Integer> {
    long count();


}
