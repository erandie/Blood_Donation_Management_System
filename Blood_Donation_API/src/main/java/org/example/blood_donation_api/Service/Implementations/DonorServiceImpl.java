package org.example.blood_donation_api.Service.Implementations;

import org.example.blood_donation_api.Entity.Donor;
import org.example.blood_donation_api.Entity.Funds;
import org.example.blood_donation_api.Repo.DonorRepo;
import org.example.blood_donation_api.Repo.FundsRepo;
import org.example.blood_donation_api.Service.DonorService;
import org.example.blood_donation_api.dto.DonorDTO;
import org.example.blood_donation_api.dto.FundsDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl {

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private FundsRepo fundsRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<DonorDTO> getAllDonors(){
        List<Donor> donors = donorRepo.findAll();
        return modelMapper.map(donors, new TypeToken<List<DonorDTO>>(){}.getType());
    }

    public DonorDTO saveDonor(DonorDTO donorDTO) {
        Donor donor = modelMapper.map(donorDTO, Donor.class);

        donorRepo.save(donor);

        for (FundsDTO fundsDTO : donorDTO.getFundsDTOS()) {
            Funds funds = modelMapper.map(fundsDTO, Funds.class);
            funds.setDonor(donor);
            fundsRepo.save(funds);
        }

        return donorDTO;

    }

    public DonorDTO updateDonors(DonorDTO donorDTO){
        donorRepo.save(modelMapper.map(donorDTO, Donor.class));
        return donorDTO;
    }

    public String deleteDonors(Integer donor_id){
        donorRepo.deleteById(donor_id);
        return "Donor details deleted!";
    }

   /* @Override
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
    }*/
}






























