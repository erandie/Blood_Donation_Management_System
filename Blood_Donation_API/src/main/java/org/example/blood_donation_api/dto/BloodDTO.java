package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BloodDTO {
    private int Blood_id;

    @NotBlank(message = "Can't be nul!")
    private String blood_group;

    @NotBlank(message = "Can't be nul!")
    @DecimalMin("0.5")
    @DecimalMax("100.0")
    private Double blood_pints;

    @NotNull(message = "Date is required")
    private Date date;

    private Integer bloodBankId;
}
