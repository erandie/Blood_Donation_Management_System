package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.BloodBankServiceImpl;
import org.example.blood_donation_api.dto.BloodBankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bloodBank")
@CrossOrigin
public class BloodBankController {

    @Autowired
    private BloodBankServiceImpl bloodBankService;

    @GetMapping("get")
    public List<BloodBankDTO> getAllBloodsInGroup(){
       return bloodBankService.getAllBloodsInGroup();
    }

    @PostMapping("save")
    public BloodBankDTO saveNewBloodsToGroup(@RequestBody BloodBankDTO bloodBankDTO){
        return bloodBankService.saveNewBloodsToGroup(bloodBankDTO);
    }

    @PutMapping("update")
    public BloodBankDTO updateBloodGroupsDetails(@RequestBody BloodBankDTO bloodBankDTO){
        return bloodBankService.updateBloodGroupsDetails(bloodBankDTO);
    }

    @DeleteMapping("delete/{bloodBank_id}")
    public String deleteBloodsFromGroups(@PathVariable Integer bloodBank_id){
        return bloodBankService.deleteBloodsFromGroups(bloodBank_id);

    }

}
