package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Integer> {
}
