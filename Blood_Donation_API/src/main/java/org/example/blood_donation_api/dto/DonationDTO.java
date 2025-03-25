package org.example.blood_donation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonationDTO {
    private int donationId;
    private Double bloodPoints;
    private String bloodType;
    private LocalDate selectedDate;

    private Integer donorId;
    private Integer empId;
}

