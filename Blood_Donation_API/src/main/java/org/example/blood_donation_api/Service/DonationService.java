package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.DonationDTO;

import java.util.List;

public interface DonationService {
    List<DonationDTO> getAllDonations();
    void saveDonations(DonationDTO donationDTO);
    void updateDonations(DonationDTO donationDTO);
    void deleteDonations(Integer donationId);
}
