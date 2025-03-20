package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Funds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundId;

    private String description;
    private Double amount;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn (name = "donorId")
    private Donor donor;

}
