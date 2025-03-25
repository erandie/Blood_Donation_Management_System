package org.example.blood_donation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private int patientId;
    private String name;
    private String email;
    private String address;

    private Integer bloodBankId;

    private List<FundsDTO> fundsDTOS = new ArrayList<>();

}
