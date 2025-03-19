package org.example.blood_donation_api.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonorDTO {
    private int Donor_id;
    private String name;
    private String email;
    private String address;

    private List<FundsDTO> fundsDTOS = new ArrayList<>();

}
