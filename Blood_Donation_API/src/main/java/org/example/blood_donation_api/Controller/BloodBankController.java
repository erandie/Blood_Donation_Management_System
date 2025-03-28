package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.BloodBankServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.BloodBankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bloodBank")
@CrossOrigin
/*(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})*/
public class BloodBankController {

    @Autowired
    private BloodBankServiceImpl bloodBankService;

    @GetMapping("get")
    public List<BloodBankDTO> getAllBloodsInGroup(){
        return bloodBankService.getAllBloodsInGroup();
    }

    @PostMapping("save")
    public ResponseUtil saveNewBloodsToGroup(@RequestBody BloodBankDTO bloodBankDTO){
        bloodBankService.saveNewBloodsToGroup(bloodBankDTO);
        return new ResponseUtil(
                201,
                "Blood Bank Saved",
                null);
    }

    @PutMapping("update")
    public ResponseUtil updateBloodGroupsDetails(@RequestBody BloodBankDTO bloodBankDTO){
         bloodBankService.updateBloodGroupsDetails(bloodBankDTO);
        return new ResponseUtil(
                200,
                "Blood Bank Updated",
                null);
    }

    @DeleteMapping("delete/{bloodBankId}")
    public ResponseUtil deleteBloodsFromGroups(@PathVariable Integer bloodBankId){
        bloodBankService.deleteBloodsFromGroups(bloodBankId);
        return new ResponseUtil(
                200,
                "Blood Bank Deleted",
                null);

    }

   /* @PostMapping("add-points")
    public ResponseUtil addBloodPoints(@RequestBody BloodBankDTO bloodBankDTO) {
        bloodBankService.addBloodPoints(bloodBankDTO.getBloodType(), bloodBankDTO.getPoints());
        return new ResponseUtil(200, "Blood points added", null);
    }*/
}



































