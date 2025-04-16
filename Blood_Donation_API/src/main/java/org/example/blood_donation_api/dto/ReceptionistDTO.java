package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceptionistDTO {
    private int receptionistId;

    @Size(min = 2, max = 50)
    @NotBlank(message = "Can't Be Null!")
    private String name;

    @Email(message = "Invalid Email!")
    @NotBlank(message = "Description is required!")
    private String email;

    @Pattern(regexp = "^[A-Za-z\\s]+$")
    @NotBlank(message = "Can't Be Null!")
    private String address;
}
