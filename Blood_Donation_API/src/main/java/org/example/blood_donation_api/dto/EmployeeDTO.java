package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private int empId;

    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name can't be blank!")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Contact is required!")
    @NotBlank(message = "Contact can't be blank!")
    private String contact;

    @NotNull(message = "Address is required!")
    @NotBlank(message = "Address can't be blank!")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address can only contain letters and spaces!")
    private String address;

    private Integer bloodBankId;

    /*private Integer donationId;*/

}


