package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Entity.Donation;
import org.example.blood_donation_api.Service.Implementations.DonationServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/donation")
@CrossOrigin
public class DonationController {

    @Autowired
    private DonationServiceImpl donationService;

    @GetMapping("get")
    public List<DonationDTO> getAllDonations() {
        return donationService.getAllDonations();
    }

    @PostMapping("save")
    public ResponseUtil saveDonations(@RequestBody DonationDTO donationDTO){
        donationService.saveDonations(donationDTO);
        return new ResponseUtil(
                201,
                "Donation Saved!",
                donationDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateDonations(@RequestBody DonationDTO donationDTO){
        donationService.updateDonations(donationDTO);
        return new ResponseUtil(
                200,
                "Donation Details Updated!",
                donationDTO
        );
    }

    @DeleteMapping("delete/{donationId}")
    public ResponseUtil deleteDonation(@PathVariable Integer donationId){
        donationService.deleteDonations(donationId);
        return new ResponseUtil(
                200,
                "Donation Details Deleted!",
                null
        );

    }

}
