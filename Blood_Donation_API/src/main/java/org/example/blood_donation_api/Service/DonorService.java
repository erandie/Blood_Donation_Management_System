package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.DonorDTO;

import java.util.List;

public interface DonorService {
    List<DonorDTO> getAllDonors();
    DonorDTO saveDonor(DonorDTO donorDTO);
    DonorDTO updateDonors(DonorDTO donorDTO);
    String deleteDonors(Integer donorId);
}
