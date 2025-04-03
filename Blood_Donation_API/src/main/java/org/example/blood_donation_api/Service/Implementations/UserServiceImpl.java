package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.User;
import org.example.blood_donation_api.Repo.UserRepo;
import org.example.blood_donation_api.Service.UserService;
import org.example.blood_donation_api.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public void saveUsers(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getUserId())){
            throw new RuntimeException("User Already Exists!");
        }

        userRepo.save(modelMapper.map(userDTO, User.class));

    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUserId())){
            throw new RuntimeException("USer Does Not Exists!");
        }

        userRepo.save(modelMapper.map(userDTO, User.class));

    }

    public void updateUser_II(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUserId())){
            throw new RuntimeException("USer Does Not Exists!");
        }

        userRepo.save(modelMapper.map(userDTO, User.class));

    }

    @Override
    public void deleteUser(Integer userId) {
        userRepo.deleteById(userId);
    }

    public UserDTO getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    public User updateStatusStatus(Integer userId, boolean active){
        User user = userRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setActive(active);
        return userRepo.save(user);
    }
}




























