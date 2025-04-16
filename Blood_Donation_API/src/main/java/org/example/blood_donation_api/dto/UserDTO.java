package org.example.blood_donation_api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blood_donation_api.Entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int userId;

    @Size(min = 2, max = 50, message = "Name must be 2–50 characters")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotNull(message = "Role is required")
    private Role role;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email can't be blank")
    private String email;

    @Pattern(regexp = "^(\\+94|0)[0-9]{9}$", message = "Invalid phone number")
    private String mobile;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address must contain only letters and spaces")
    @NotBlank(message = "Address can't be blank")
    private String address;

    @Size(min = 6, max = 20, message = "Password must be 6–20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$", message = "Must have letters & numbers")
    @NotBlank(message = "Password can't be blank")
    private String password;

    private String profileImagePath;

    private boolean active;
}
