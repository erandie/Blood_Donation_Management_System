package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(Integer userId);
    User findByEmail(String userName);

    boolean existsByEmail(String userName);

}
