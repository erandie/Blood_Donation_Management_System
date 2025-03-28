package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.UserServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("get")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("get/{userId}")
    public ResponseUtil getUserById(@PathVariable int userId) {
        UserDTO user = userService.getUserById(userId);
        return new ResponseUtil(200, "User Found", user);
    }


    @PostMapping("save")
    public ResponseUtil saveUsers(@RequestBody UserDTO userDTO){
        userService.saveUsers(userDTO);
        return new ResponseUtil(
                201,
                "User Added!",
                userDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseUtil(
                200,
                "User Updated!",
                userDTO
        );
    }

    @PutMapping("update/{userId}")
    public ResponseUtil updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        userDTO.setUserId(userId);
        userService.updateUser_II(userDTO);
        return new ResponseUtil(200, "User Updated!", userDTO);
    }


    @DeleteMapping("delete/{userId}")
    public ResponseUtil deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseUtil(
                200,
                "User Deleted!",
                null
        );
    }


}
