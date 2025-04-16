package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonationDTO {
    private int donationId;

    @DecimalMin("0.5")
    @DecimalMax("100.0")
    @NotNull(message = "Can't Be Null!")
    private Double bloodPoints;

    @NotBlank(message = "Can't Be Null!")
    private String bloodType;

    @NotNull(message = "Can't Be Null!")
    private LocalDate selectedDate;

    private Integer donorId;
    private Integer empId;
}

