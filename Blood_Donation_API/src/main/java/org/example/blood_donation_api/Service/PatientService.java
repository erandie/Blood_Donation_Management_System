package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO savePatients(PatientDTO patientDTO);
    PatientDTO updatePatients(PatientDTO patientDTO);
    String deletePatient(Integer patientId);
}
