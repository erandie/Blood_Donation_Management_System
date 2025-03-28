package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Entity.Role;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/role")
@CrossOrigin
public class UserRoleController {

    @GetMapping("get")
    public List<Role> getRoles(){
        return Arrays.asList(Role.values());
    }
}
