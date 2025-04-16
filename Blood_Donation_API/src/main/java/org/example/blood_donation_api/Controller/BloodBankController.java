package org.example.blood_donation_api.Controller;

import jakarta.validation.Valid;
import org.example.blood_donation_api.Repo.BloodBankRepo;
import org.example.blood_donation_api.Service.Implementations.BloodBankServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.BloodBankDTO;
import org.example.blood_donation_api.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bloodBank")
@CrossOrigin
@Validated
public class BloodBankController {

    @Autowired
    private BloodBankServiceImpl bloodBankServiceImpl;

    @GetMapping("get")
    public List<BloodBankDTO> getAllBloodsInGroup() {
        return bloodBankServiceImpl.getAllBloodsInGroup();
    }

    @PostMapping("save")
    public ResponseUtil saveNewBloodsToGroup(@Valid @RequestBody BloodBankDTO bloodBankDTO, BindingResult bindingResult) {
        bloodBankServiceImpl.saveNewBloodsToGroup(bloodBankDTO);
        return new ResponseUtil(
                201,
                "Blood Bank Saved",
                bloodBankDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateBloodGroupsDetails(@Valid @RequestBody BloodBankDTO bloodBankDTO, BindingResult bindingResult) {
        bloodBankServiceImpl.updateBloodGroupsDetails(bloodBankDTO);

        return new ResponseUtil(
                200,
                "Blood Bank Updated",
                null
        );
    }

    @DeleteMapping("delete/{bloodBankId}")
    public ResponseUtil deleteBloodsFromGroups(@PathVariable Integer bloodBankId) {
        bloodBankServiceImpl.deleteBloodsFromGroups(bloodBankId);
        return new ResponseUtil(
                200,
                "Blood Bank Deleted",
                null
        );
    }

    @PostMapping("add-points")
    public ResponseUtil addBloodPoints(@RequestBody BloodBankDTO bloodBankDTO) {
        bloodBankServiceImpl.addBloodPoints(bloodBankDTO.getBloodType(), bloodBankDTO.getPoints());
        return new ResponseUtil(
                200,
                "Blood points added",
                null
        );
    }

    @GetMapping("bloodStock")
    public ResponseUtil getBloodStock(){
        double bloodStock = bloodBankServiceImpl.getBloodStock();
        return new ResponseUtil(
                200,
                "Total Bloods We Have",
                bloodStock
        );
    }

    @GetMapping("search")
    public ResponseUtil searchBloodType(@RequestParam String bloodType){
        List<BloodBankDTO> bloodTypes = bloodBankServiceImpl.searchByBloodType(bloodType);

        return new ResponseUtil(
                200,
                "Here You Go!!",
                bloodTypes
        );
    }

}



























