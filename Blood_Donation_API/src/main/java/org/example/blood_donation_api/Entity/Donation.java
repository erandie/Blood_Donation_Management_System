package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private Double bloodPoints;

    @Enumerated(EnumType.STRING)
    private BloodTypes bloodType;

    private LocalDate selectedDate;

    @ManyToOne
    @JoinColumn(name = "donorId")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

}

