package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.PatientServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
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
    public ResponseUtil savePatient(@RequestBody PatientDTO patientDTO) {
        patientService.savePatients(patientDTO);
        return new ResponseUtil(
                201,
                "Patient saved!",
                patientDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updatePatient(@RequestBody PatientDTO patientDTO){
        patientService.updatePatients(patientDTO);
        return new ResponseUtil(
                200,
                "Patient Updated",
                patientDTO
        );
    }

    @DeleteMapping("delete/{patientId}")
    public ResponseUtil deletePatient(@PathVariable Integer patientId){
        patientService.deletePatient(patientId);
        return new ResponseUtil(
                200,
                "Patient Deleted",
                null
        );
    }

}
