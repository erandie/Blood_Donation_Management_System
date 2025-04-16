package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundsDTO {
    private int fundId;

    @NotBlank(message = "Description is required!")
    @Size(min = 4, max = 200, message = "Desc must be 4 and 200 characters!")
    private String description;

    @DecimalMin(value = "0.01", message = "Amount must be grater that 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid amount format")
    @NotNull(message = "Description is required!")
    private Double amount;

    @NotBlank(message = "Cant Be Null!")
    private String paymentMethod;

    private Integer donorId;

    private Integer patientId;


}
