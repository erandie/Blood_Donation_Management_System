package org.example.blood_donation_api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blood_donation_api.Entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int userId;
    private String name;
    private Role role;
    private String email;
    private int mobile;
    private String address;
    private String password;
    private String profileImagePath;

    private boolean active;

}
