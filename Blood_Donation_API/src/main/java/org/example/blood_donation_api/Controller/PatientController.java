package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.PatientServiceImpl;
import org.example.blood_donation_api.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientServiceImpl patientService;

    @GetMapping("get")
    public List<PatientDTO> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("save")
    public PatientDTO savePatient(@RequestBody PatientDTO patientDTO) {
        return patientService.savePatients(patientDTO);
    }

    @PutMapping("update")
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO){
        return patientService.updatePatients(patientDTO);
    }

    @DeleteMapping("delete/{patientId}")
    public String deletePatient(@PathVariable Integer patientId){
        return patientService.deletePatient(patientId);
    }

}
