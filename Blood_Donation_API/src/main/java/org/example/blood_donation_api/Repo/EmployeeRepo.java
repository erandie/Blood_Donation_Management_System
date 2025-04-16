package org.example.blood_donation_api.Repo;

import org.example.blood_donation_api.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCase(String name);
}
