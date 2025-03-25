package org.example.blood_donation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private int empId;
    private String name;
    private String contact;
    private String address;

    private Integer bloodBankId;

    private Integer donationId;

}


