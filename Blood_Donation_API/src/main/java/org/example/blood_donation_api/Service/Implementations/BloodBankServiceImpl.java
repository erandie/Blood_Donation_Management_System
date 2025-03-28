package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.*;
import org.example.blood_donation_api.Repo.BloodBankRepo;
import org.example.blood_donation_api.Repo.BloodRepo;
import org.example.blood_donation_api.Repo.EmployeeRepo;
import org.example.blood_donation_api.Repo.ReceptionistRepo;
import org.example.blood_donation_api.Service.BloodBankService;
import org.example.blood_donation_api.dto.BloodBankDTO;
import org.example.blood_donation_api.dto.BloodDTO;
import org.example.blood_donation_api.dto.EmployeeDTO;
import org.example.blood_donation_api.dto.ReceptionistDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BloodBankServiceImpl implements BloodBankService {

    @Autowired
    private BloodBankRepo bloodBankRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BloodRepo bloodRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReceptionistRepo receptionistRepo;

    @Override
    public List<BloodBankDTO> getAllBloodsInGroup(){
        List<BloodBank> bloodBanks = bloodBankRepo.findAll();
        return modelMapper.map(bloodBanks, new TypeToken<List<BloodBankDTO>>(){}.getType());
    }

    @Override
    public void saveNewBloodsToGroup(BloodBankDTO bloodBankDTO){
        if (bloodBankRepo.existsById(bloodBankDTO.getBloodBankId())) {
            throw new RuntimeException("Blood Bank Already Exists");
        }
        bloodBankRepo.save(modelMapper.map(bloodBankDTO, BloodBank.class));
    }


    @Override
    public void updateBloodGroupsDetails(BloodBankDTO bloodBankDTO) {
        if (!bloodBankRepo.existsById(bloodBankDTO.getBloodBankId())) {
            throw new RuntimeException("Blood Bank does not Exist");
        }
        bloodBankRepo.save(modelMapper.map(bloodBankDTO, BloodBank.class));
    }


    @Override
    public void deleteBloodsFromGroups(Integer bloodBankId){
        bloodBankRepo.deleteById(bloodBankId);
    }

    public void addBloodPoints(String bloodType, Double points) {
        // Find existing blood group or create new
        BloodBank bloodBank = bloodBankRepo.findByBloodType(BloodTypes.valueOf(bloodType))
                .orElseGet(() -> {
                    BloodBank newBank = new BloodBank();
                    newBank.setBloodType(BloodTypes.valueOf(bloodType));
                    newBank.setPoints(0.0);
                    return bloodBankRepo.save(newBank);
                });

        bloodBank.addPoints(points);
        bloodBankRepo.save(bloodBank);
    }


}































