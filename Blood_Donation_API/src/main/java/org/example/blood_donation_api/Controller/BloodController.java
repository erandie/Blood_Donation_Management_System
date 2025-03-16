package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.BloodService;
import org.example.blood_donation_api.Service.Implementations.BloodServiceImpl;
import org.example.blood_donation_api.dto.BloodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/blood")
public class BloodController {

    @Autowired
    private BloodServiceImpl bloodService;

    @GetMapping("get")
    public List<BloodDTO> getAllBloodDetails(){
        return bloodService.getAllBloodDetails();
    }

    @PostMapping("save")
    public BloodDTO saveBloods(@RequestBody BloodDTO bloodDTO){
        return bloodService.saveBloods(bloodDTO);
    }

    @PutMapping("update")
    public BloodDTO updateBloods(@RequestBody BloodDTO bloodDTO){
        return bloodService.updateBloods(bloodDTO);
    }

    @DeleteMapping("delete/{Blood_id}")
    public String deleteBloods(@PathVariable Integer Blood_id) {
        return bloodService.deleteBloods(Blood_id);
    }

}


























