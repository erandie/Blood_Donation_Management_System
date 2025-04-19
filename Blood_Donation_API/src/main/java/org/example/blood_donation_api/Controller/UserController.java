package org.example.blood_donation_api.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.blood_donation_api.Entity.User;
import org.example.blood_donation_api.Service.Implementations.UserServiceImpl;
import org.example.blood_donation_api.Util.JwtUtil;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.AuthDTO;
import org.example.blood_donation_api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.blood_donation_api.Util.VarList;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
@Validated
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

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
    public ResponseEntity<ResponseUtil> saveUsers(@RequestBody @Valid UserDTO userDTO){

        try {
            int res = userService.saveUsers(userDTO);
            switch (res){
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseUtil(VarList.Created, "Success!", authDTO));
                }

                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseUtil(VarList.Not_Acceptable, "Email Already Used!", null));
                }

                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseUtil(VarList.Bad_Gateway, "Error!", null));
                }

            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(VarList.Internal_Server_Error, e.getMessage(), null));
        }

        /*userService.saveUsers(userDTO);
        return new ResponseUtil(
                201,
                "User Added!",
                userDTO
        );*/
    }

    @PutMapping("update")
    public ResponseUtil updateUser(@Valid @RequestBody UserDTO userDTO) {
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

    public ResponseEntity<String> updateUserStatus(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        User updateUser = userService.updateStatusStatus(userId, userDTO.isActive());
        String status = updateUser.isActive() ? "activated" : "deactivated";
        return ResponseEntity.ok("User : " + status + " successfully.");
    }

    @GetMapping("search")
    public ResponseUtil searchByName(@RequestParam String name){
        List<UserDTO> users = userService.searchByName(name);
        return new ResponseUtil(
                200,
                "Here You Goooow!!!!!",
                users
        );
    }

    @GetMapping("info")
    public ResponseEntity<UserDTO> getLoggedUserInfo(HttpServletRequest request) {
        String email = jwtUtil.extractUsernameFromRequest(request);
        UserDTO user = userService.getUsernameByUserDTO(email);
        return ResponseEntity.ok(user);
    }





}

































