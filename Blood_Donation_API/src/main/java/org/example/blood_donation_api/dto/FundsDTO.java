package org.example.blood_donation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundsDTO {
    private int Fund_id;
    private String description;
    private Double amount;
    private String paymentMethode;
}
