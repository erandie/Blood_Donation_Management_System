package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.DonorDTO;

import java.util.List;

public interface DonorService {
    void addDonor(DonorDTO donorDTO);
    void updateDonor(DonorDTO donorDTO);
    void deleteDonor(int Donor_id);
    List<DonorDTO> getAllDonors();
}
