package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.DonationDTO;

import java.util.List;

public interface DonationService {
    List<DonationDTO> getAllDonations();
    DonationDTO saveDonations(DonationDTO donationDTO);
    DonationDTO updateDonations(DonationDTO donationDTO);
    String deleteDonations(Integer donationId);
}
