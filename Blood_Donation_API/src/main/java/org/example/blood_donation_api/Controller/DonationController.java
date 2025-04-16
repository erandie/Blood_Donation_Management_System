package org.example.blood_donation_api.Controller;

import jakarta.validation.Valid;
import org.example.blood_donation_api.Entity.Donation;
import org.example.blood_donation_api.Service.Implementations.DonationServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/donation")
@CrossOrigin
@Validated
public class DonationController {

    @Autowired
    private DonationServiceImpl donationService;

    @GetMapping("get")
    public List<DonationDTO> getAllDonations() {
        return donationService.getAllDonations();
    }

    @PostMapping("save")
    public ResponseUtil saveDonations(@Valid @RequestBody DonationDTO donationDTO, BindingResult bindingResult){
        donationService.saveDonations(donationDTO);
        return new ResponseUtil(
                201,
                "Donation Saved!",
                donationDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateDonations(@Valid @RequestBody DonationDTO donationDTO){
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

    @GetMapping("donationCount")
    public ResponseUtil getTotalDonations(){
        long count = donationService.getDonationCount();
        return new ResponseUtil(
                200,
                "Donation Count",
                count
        );
    }

    @GetMapping("search")
    public ResponseUtil searchDonationById(@RequestParam Integer donationId){
        DonationDTO donations = donationService.searchById(donationId);
        return new ResponseUtil(
                200,
                "Here You Gooo!!",
                donations
        );
    }

}
