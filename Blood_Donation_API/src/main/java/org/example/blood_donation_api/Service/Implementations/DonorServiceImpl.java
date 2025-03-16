package org.example.blood_donation_api.Service.Implementations;

import org.example.blood_donation_api.Entity.Donor;
import org.example.blood_donation_api.Repo.DonorRepo;
import org.example.blood_donation_api.Service.DonorService;
import org.example.blood_donation_api.dto.DonorDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addDonor(DonorDTO donorDTO) {
        if (donorRepo.existsById(donorDTO.getDonor_id())) {
            throw new RuntimeException("Donor Already Exists!");
        }
    }

    @Override
    public void updateDonor(DonorDTO donorDTO) {
        if (donorRepo.existsById(donorDTO.getDonor_id())) {
            donorRepo.save(modelMapper.map(donorDTO, Donor.class));
        }
    }

    @Override
    public void deleteDonor(int Donor_id) {
        donorRepo.deleteById(Donor_id);
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        return modelMapper.map(donorRepo.findAll(),
                new TypeToken<List<DonorDTO>>() {}.getType());
    }
}






























