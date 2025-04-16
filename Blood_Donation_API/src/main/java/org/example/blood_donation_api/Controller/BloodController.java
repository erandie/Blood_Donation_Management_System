package org.example.blood_donation_api.Controller;

import jakarta.validation.Valid;
import org.example.blood_donation_api.Service.BloodService;
import org.example.blood_donation_api.Service.Implementations.BloodServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.BloodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/blood")
@Validated
public class BloodController {

    @Autowired
    private BloodServiceImpl bloodService;

    @GetMapping("get")
    public List<BloodDTO> getAllBloodDetails(){
        return bloodService.getAllBloodDetails();
    }

    @PostMapping("save")
    public ResponseUtil saveBloods(@Valid @RequestBody BloodDTO bloodDTO){
        bloodService.saveBloods(bloodDTO);
        return new ResponseUtil(
                201,
                "Blood Saved!",
                null
        );
    }

    @PutMapping("update")
    public ResponseUtil updateBloods(@Valid @RequestBody BloodDTO bloodDTO){
        bloodService.updateBloods(bloodDTO);
        return new ResponseUtil(
                200,
                "Blood Updated!",
                null
        );
    }

    @DeleteMapping("delete/{Blood_id}")
    public ResponseUtil deleteBloods(@PathVariable Integer Blood_id) {
        bloodService.deleteBloods(Blood_id);
        return new ResponseUtil(
                200,
                "Blood Deleted!",
                null
        );
    }

}


























