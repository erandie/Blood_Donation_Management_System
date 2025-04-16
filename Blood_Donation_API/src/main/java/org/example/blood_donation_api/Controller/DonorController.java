package org.example.blood_donation_api.Controller;

import jakarta.validation.Valid;
import org.example.blood_donation_api.Service.Implementations.DonorServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.DonorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/donor")
@CrossOrigin
@Validated
public class DonorController {

    @Autowired
    private DonorServiceImpl donorService;

    @GetMapping("get")
    public List<DonorDTO> getAllDonors() {
        return donorService.getAllDonors();
    }

    @PostMapping("save")
    public ResponseUtil saveDonors(@Valid @RequestBody DonorDTO donorDTO) {
        donorService.saveDonor(donorDTO);
        return new ResponseUtil(
                201,
                "Donor saved!",
                donorDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateDonors(@Valid @RequestBody DonorDTO donorDTO) {
        donorService.updateDonors(donorDTO);
        return new ResponseUtil(
                200,
                "donor updated!",
                donorDTO
        );
    }

    @DeleteMapping("delete/{donorId}")
    public ResponseUtil deleteDonors(@PathVariable Integer donorId) {
        donorService.deleteDonors(donorId);
        return new ResponseUtil(
                200,
                "Donor deleted!",
                null
        );

    }

    @GetMapping("donorCount")
    public ResponseUtil getDonorCount(){
        long count = donorService.donorCount();
        return new ResponseUtil(
                200,
                "Donors Count",
                count
        );
    }

    @GetMapping("search")
    public ResponseUtil searchDonors(@RequestParam String name){
        List<DonorDTO> donors = donorService.searchByName(name);
        return new ResponseUtil(
                200,
                "Here You Goooow!!!!!",
                donors
        );
    }

}









