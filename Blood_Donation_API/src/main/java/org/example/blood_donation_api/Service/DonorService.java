package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.DonorDTO;

import java.util.List;

public interface DonorService {
    List<DonorDTO> getAllDonors();
    void saveDonor(DonorDTO donorDTO);
    void updateDonors(DonorDTO donorDTO);
    void deleteDonors(Integer donorId);
}
