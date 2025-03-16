package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Patient;
import org.example.blood_donation_api.Repo.PatientRepo;
import org.example.blood_donation_api.dto.BloodDTO;
import org.example.blood_donation_api.dto.PatientDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<PatientDTO> getAllPatients(){
        List<Patient> patientList = patientRepo.findAll();
        return modelMapper.map(patientList, new TypeToken<List<PatientDTO>>(){}.getType());
    }

    public PatientDTO savePatients(PatientDTO patientDTO) {
        patientRepo.save(modelMapper.map(patientDTO, Patient.class));
        return patientDTO;
    }

    public PatientDTO updatePatients(PatientDTO patientDTO) {
        patientRepo.save(modelMapper.map(patientDTO, Patient.class));
        return patientDTO;
    }

    public String deletePatient(Integer Patient_id){
        patientRepo.deleteById(Patient_id);
        return "Patient Details Deleted!";
    }


}


























