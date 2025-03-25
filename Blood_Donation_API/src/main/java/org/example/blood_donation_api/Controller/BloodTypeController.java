package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Entity.BloodTypes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/bloodTypes")
@CrossOrigin
public class BloodTypeController {

    @GetMapping("get")
    public List<BloodTypes> getBloodTypes(){
        return Arrays.asList(BloodTypes.values());
    }
}
