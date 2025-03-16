package org.example.blood_donation_api.dto;

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
    private String blood_group;
    private Double blood_pints;
    private Date date;
}
