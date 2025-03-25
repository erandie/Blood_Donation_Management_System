package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    private String name;
    private String contact;
    private String address;

    @ManyToOne
    @JoinColumn(name = "bloodBank_id")
    private BloodBank bloodBank;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donation> donations = new ArrayList<>();
}


