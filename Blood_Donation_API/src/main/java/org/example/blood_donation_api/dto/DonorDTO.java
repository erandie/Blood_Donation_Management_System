package org.example.blood_donation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DonorDTO {
    private int Donor_id;
    private String name;
    private String email;
    private String address;

}
