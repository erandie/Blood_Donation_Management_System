package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();
    void savePatients(PatientDTO patientDTO);
    void updatePatients(PatientDTO patientDTO);
    void deletePatient(Integer patientId);
}
