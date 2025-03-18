package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.BloodBank;
import org.example.blood_donation_api.Repo.BloodBankRepo;
import org.example.blood_donation_api.Repo.EmployeeRepo;
import org.example.blood_donation_api.dto.BloodBankDTO;
import org.example.blood_donation_api.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BloodBankServiceImpl {

    @Autowired
    private BloodBankRepo bloodBankRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<BloodBankDTO> getAllBloodsInGroup(){
        List<BloodBank> bloodBanks = bloodBankRepo.findAll();
        return modelMapper.map(bloodBanks, new TypeToken<List<BloodBankDTO>>(){}.getType());
    }

    public BloodBankDTO saveNewBloodsToGroup(BloodBankDTO bloodBankDTO){
        bloodBankRepo.save(modelMapper.map(bloodBankDTO, BloodBank.class));
        return bloodBankDTO;
    }

    public BloodBankDTO updateBloodGroupsDetails(BloodBankDTO bloodBankDTO){
        bloodBankRepo.save(modelMapper.map(bloodBankDTO, BloodBank.class));
        return bloodBankDTO;
    }

    public String deleteBloodsFromGroups(Integer bloodBank_id){
        bloodBankRepo.deleteById(bloodBank_id);
        return "Bloods deleted!";
    }


}
