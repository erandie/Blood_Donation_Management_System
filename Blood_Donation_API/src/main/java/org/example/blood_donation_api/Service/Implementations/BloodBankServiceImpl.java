package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Blood;
import org.example.blood_donation_api.Entity.BloodBank;
import org.example.blood_donation_api.Entity.Employee;
import org.example.blood_donation_api.Entity.Receptionist;
import org.example.blood_donation_api.Repo.BloodBankRepo;
import org.example.blood_donation_api.Repo.BloodRepo;
import org.example.blood_donation_api.Repo.EmployeeRepo;
import org.example.blood_donation_api.Repo.ReceptionistRepo;
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

@Service
@Transactional
public class BloodBankServiceImpl {

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

    public String deleteBloodsFromGroups(Integer bloodBankId){
        bloodBankRepo.deleteById(bloodBankId);
        return "Bloods deleted!";
    }

}
