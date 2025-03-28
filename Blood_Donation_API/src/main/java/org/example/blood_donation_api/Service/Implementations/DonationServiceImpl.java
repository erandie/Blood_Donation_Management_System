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
    public void saveDonations(DonationDTO donationDTO) {
       if (donationRepo.existsById(donationDTO.getDonationId())) {
           throw new RuntimeException("Donation Already Exists!");
       }
        donationRepo.save(modelMapper.map(donationDTO, Donation.class));
    }

    @Override
    public void updateDonations(DonationDTO donationDTO) {
        if (!donationRepo.existsById(donationDTO.getDonationId())) {
            throw new RuntimeException("Donation does not exist!");
        }
        donationRepo.save(modelMapper.map(donationDTO, Donation.class));
    }


    @Override
    public void deleteDonations(Integer donationId) {
        donationRepo.deleteById(donationId);
    }

}




























