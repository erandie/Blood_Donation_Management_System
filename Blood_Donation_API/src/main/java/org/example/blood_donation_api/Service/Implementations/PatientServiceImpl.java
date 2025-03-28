package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Patient;
import org.example.blood_donation_api.Repo.PatientRepo;
import org.example.blood_donation_api.Service.PatientService;
import org.example.blood_donation_api.dto.BloodDTO;
import org.example.blood_donation_api.dto.PatientDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients(){
        List<Patient> patientList = patientRepo.findAll();
        return modelMapper.map(patientList, new TypeToken<List<PatientDTO>>(){}.getType());
    }

    @Override
    public void savePatients(PatientDTO patientDTO) {
        if (patientRepo.existsById(patientDTO.getPatientId())){
            throw new RuntimeException("Patient Already Exists!");
        }
        patientRepo.save(modelMapper.map(patientDTO, Patient.class));
    }

    @Override
    public void updatePatients(PatientDTO patientDTO) {
        if (!patientRepo.existsById(patientDTO.getPatientId())){
            throw new RuntimeException("Patient does not Exists!");
        }
        patientRepo.save(modelMapper.map(patientDTO, Patient.class));
    }

    @Override
    public void deletePatient(Integer patientId){
        patientRepo.deleteById(patientId);
    }


}


























