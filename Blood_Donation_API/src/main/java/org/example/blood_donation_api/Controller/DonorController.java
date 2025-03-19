package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.DonorServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.DonorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/donor")
public class DonorController {

    @Autowired
    private DonorServiceImpl donorService;

    @GetMapping("get")
    public List<DonorDTO> getAllDonors() {
        return donorService.getAllDonors();
    }

    @PostMapping("save")
    public DonorDTO saveDonors(@RequestBody DonorDTO donorDTO) {
        return donorService.saveDonor(donorDTO);
    }

    @PutMapping("update")
    public DonorDTO updateDonors(@RequestBody DonorDTO donorDTO) {
        return donorService.updateDonors(donorDTO);
    }

    @DeleteMapping("delete/{donor_id}")
    public String deleteDonors(@PathVariable Integer donor_id) {
        donorService.deleteDonors(donor_id);
        return "Donor deleted!";
}

























    /*@PostMapping("save")
    public ResponseUtil saveDonor(@RequestBody DonorDTO donorDTO) {
        donorService.addDonor(donorDTO);
        return new ResponseUtil(201, "Donor Saved!", null);
    }

    @PutMapping("update")
    public ResponseUtil updateDonor(@RequestBody DonorDTO donorDTO){
        donorService.updateDonor(donorDTO);
        return new ResponseUtil(200, "Donor Updated!", null);
    }

    @DeleteMapping(path = "delete/{Donor_id}")
    public ResponseUtil deleteDonor(@PathVariable("Donor_id") int Donor_id) {
        donorService.deleteDonor(Donor_id);
        return new ResponseUtil(200, "Donor Deleted!", null);
    }

    *//*@GetMapping("getAll")
    public ResponseUtil getAllDonors(){
        return new ResponseUtil(200, "Donor Liiist!", null);
    }*//*

    @GetMapping("get")
    public List<DonorDTO> getAllDonors(){
        List<DonorDTO> donorDTOS=donorService.getAllDonors();
        return donorDTOS;
    }*/


}
