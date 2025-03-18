package org.example.blood_donation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BloodBankDTO {
    private int bloodBank_id;
    private Date date;
    private Double points;

    private List<EmployeeDTO> employeeDTOS= new ArrayList<>();

}
