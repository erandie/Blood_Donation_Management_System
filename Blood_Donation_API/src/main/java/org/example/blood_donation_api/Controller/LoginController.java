package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.UserServiceImpl;
import org.example.blood_donation_api.Util.JwtUtil;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.Util.VarList;
import org.example.blood_donation_api.dto.AuthDTO;
import org.example.blood_donation_api.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class LoginController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final ResponseUtil responseUtil;


    public LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserServiceImpl userService, ResponseUtil responseUtil) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.responseUtil = responseUtil;
    }

    @PostMapping("login")
    public ResponseEntity<ResponseUtil> login(@RequestBody UserDTO userDTO){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseUtil(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }

        UserDTO loadedUser = userService.loadUserDetailsByUSerName(userDTO.getEmail());
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Login Failed! Try Again!", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseUtil(VarList.Conflict, "Login Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail(loadedUser.getEmail());
        authDTO.setToken(token);
        authDTO.setRole(String.valueOf(loadedUser.getRole()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseUtil(VarList.Created, "Success!", authDTO));

    }

}



//ui hdnna one
//jwt apply krnn one
//search hdnna one
//validation hdnna one
//transaction ekk ghnna blood gttm bank eke adu wena widiyta
//dashboard deka loard krnna login ekta set wena widiyt


























