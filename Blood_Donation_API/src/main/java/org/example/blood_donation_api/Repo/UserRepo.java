package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
