package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    int saveUsers(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(Integer userId);
}
