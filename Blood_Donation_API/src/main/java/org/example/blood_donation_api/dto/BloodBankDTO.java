package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Can't be nul!")
    private String bloodType;

    @NotNull(message = "Pints is required!")
    @DecimalMin("0.5")
    @DecimalMax("100.0")
    private Double points;

    private Integer empId;
    private Integer patientId;



    /*private List<EmployeeDTO> employeeDTOS = new ArrayList<>();*/

    private ReceptionistDTO receptionistDTO;

    private List<BloodDTO> bloodDTOS = new ArrayList<>();

}
