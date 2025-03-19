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

   /* public BloodBankDTO saveNewBloodsToGroup(BloodBankDTO bloodBankDTO){
        BloodBank bloodBank = modelMapper.map(bloodBankDTO, BloodBank.class);

        bloodBankRepo.save(bloodBank);



        *//*for (EmployeeDTO employeeDTO : bloodBankDTO.getEmployeeDTOS()) {
            Employee employee = modelMapper.map(employeeDTO, Employee.class);
            employee.setBloodBank(bloodBank); // Set the association to BloodBank
            employeeRepo.save(employee); // Save employee to DB
        }*//*

       *//* // Save Blood entities and map back to BloodDTOs
        for (BloodDTO bloodDTO : bloodBankDTO.getBloodDTOS()){
            Blood blood = modelMapper.map(bloodDTO, Blood.class);
            blood.setBloodBank(bloodBank); // Set the association to BloodBank
            bloodRepo.save(blood); // Save blood to DB
        }*//*

        BloodBankDTO savedBloodBankDTO = modelMapper.map(bloodBank, BloodBankDTO.class);

        if (bloodBank.getReceptionist() != null) {
            savedBloodBankDTO.setReceptionistDTO(modelMapper.map(bloodBank.getReceptionist(), ReceptionistDTO.class));
        } else {
            savedBloodBankDTO.setReceptionistDTO(null); // Ensure null is set if no receptionist
        }

        *//*List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : bloodBank.getEmployees()) {
            employeeDTOS.add(modelMapper.map(employee, EmployeeDTO.class));
        }
        savedBloodBankDTO.setEmployeeDTOS(employeeDTOS);*//*

       *//* List<BloodDTO> bloodDTOS = new ArrayList<>();
        for (Blood blood : bloodBank.getBloods()) {
            bloodDTOS.add(modelMapper.map(blood, BloodDTO.class));
        }
        savedBloodBankDTO.setBloodDTOS(bloodDTOS);*//*

        return savedBloodBankDTO;
    }*/


    public BloodBankDTO updateBloodGroupsDetails(BloodBankDTO bloodBankDTO){
        bloodBankRepo.save(modelMapper.map(bloodBankDTO, BloodBank.class));
        return bloodBankDTO;
    }

    public String deleteBloodsFromGroups(Integer bloodBank_id){
        bloodBankRepo.deleteById(bloodBank_id);
        return "Bloods deleted!";
    }


}
