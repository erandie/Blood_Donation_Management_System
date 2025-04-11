package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Role;
import org.example.blood_donation_api.Entity.User;
import org.example.blood_donation_api.Repo.UserRepo;
import org.example.blood_donation_api.Service.UserService;
import org.example.blood_donation_api.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

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
        if (userRepo.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("User Already Exists!");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setRole(Role.USER);
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

    public UserDTO loadUserDetailsByUSerName(String userName){
        User user = userRepo.findByEmail(userName);
        return modelMapper.map(user, UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name())); //Convert Enum to String
        return authorities;
    }

    private UserDTO searchUser(String userName) {
        if (userRepo.existsByEmail(userName)){
            User user = userRepo.findByEmail(userName);
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }
}




























