package org.example.blood_donation_api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
