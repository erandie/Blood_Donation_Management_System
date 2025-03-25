package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Entity.Donation;
import org.example.blood_donation_api.Service.Implementations.DonationServiceImpl;
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
    public DonationDTO saveDonations(@RequestBody DonationDTO donationDTO){
        return donationService.saveDonations(donationDTO);
    }

    @PutMapping("update")
    public DonationDTO updateDonations(@RequestBody DonationDTO donationDTO){
        return donationService.updateDonations(donationDTO);
    }

    @DeleteMapping("delete/{donationId}")
    public String deleteDonation(@PathVariable Integer donationId){
        return donationService.deleteDonations(donationId);

    }

}
