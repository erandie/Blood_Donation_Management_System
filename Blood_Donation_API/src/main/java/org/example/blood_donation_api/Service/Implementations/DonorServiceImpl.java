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
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private FundsRepo fundsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DonorDTO> getAllDonors() {
        List<Donor> donors = donorRepo.findAll();
        return modelMapper.map(donors, new TypeToken<List<DonorDTO>>() {
        }.getType());
    }

    @Override
    public void saveDonor(DonorDTO donorDTO) {
        if (donorRepo.existsById(donorDTO.getDonorId())){
            throw new RuntimeException("Donor Already Exists!");
        }
        donorRepo.save(modelMapper.map(donorDTO, Donor.class));

       /* donorRepo.save(donor);*/

       /* for (FundsDTO fundsDTO : donorDTO.getFundsDTOS()) {
            Funds funds = modelMapper.map(fundsDTO, Funds.class);
            funds.setDonor(donor);
            fundsRepo.save(funds);
        }*/


    }

    @Override
    public void updateDonors(DonorDTO donorDTO) {
        if (!donorRepo.existsById(donorDTO.getDonorId())) {
            throw new RuntimeException("Donor does not exist!");
        }
        donorRepo.save(modelMapper.map(donorDTO, Donor.class));
    }

    @Override
    public void deleteDonors(Integer donorId) {
        donorRepo.deleteById(donorId);
    }

}






























