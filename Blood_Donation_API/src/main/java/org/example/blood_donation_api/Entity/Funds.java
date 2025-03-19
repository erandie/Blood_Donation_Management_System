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
    private int Fund_id;

    private String description;
    private Double amount;
    private String paymentMethode;

    @ManyToOne
    @JoinColumn (name = "donor_id")
    private Donor donor;

}
