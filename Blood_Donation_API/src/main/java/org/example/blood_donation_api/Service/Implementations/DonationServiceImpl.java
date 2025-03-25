package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Donation;
import org.example.blood_donation_api.Repo.DonationRepo;
import org.example.blood_donation_api.Service.DonationService;
import org.example.blood_donation_api.dto.DonationDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DonationDTO> getAllDonations() {
        List<Donation> donations = donationRepo.findAll();
        return modelMapper.map(donations, new TypeToken<List<DonationDTO>>(){}.getType());
    }

    @Override
    public DonationDTO saveDonations(DonationDTO donationDTO) {
        donationRepo.save(modelMapper.map(donationDTO, Donation.class));
        return donationDTO;
    }

    @Override
    public DonationDTO updateDonations(DonationDTO donationDTO) {
        donationRepo.save(modelMapper.map(donationDTO, Donation.class));
        return donationDTO;
    }
    @Override
    public String deleteDonations(Integer donationId) {
        donationRepo.deleteById(donationId);
        return "Donation Deleted SuccessFully!";
    }

}




























