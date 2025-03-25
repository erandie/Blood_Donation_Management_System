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
    private int bloodBankId;
    private String bloodType;
    private Double points;

    private Integer empId;
    private Integer patientId;



    /*private List<EmployeeDTO> employeeDTOS = new ArrayList<>();*/

    private ReceptionistDTO receptionistDTO;

    private List<BloodDTO> bloodDTOS = new ArrayList<>();

}
