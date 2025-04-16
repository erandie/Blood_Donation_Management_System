package org.example.blood_donation_api.Service.Implementations;

import org.example.blood_donation_api.Entity.BloodBank;
import org.example.blood_donation_api.Entity.BloodTypes;
import org.example.blood_donation_api.Repo.BloodBankRepo;
import org.example.blood_donation_api.Service.BloodBankService;
import org.example.blood_donation_api.dto.BloodBankDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    @Autowired
    private BloodBankRepo bloodBankRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BloodBankDTO> getAllBloodsInGroup() {
        List<BloodBank> bloodBanks = bloodBankRepo.findAll();
        return modelMapper.map(bloodBanks, new TypeToken<List<BloodBankDTO>>() {}.getType());
    }
    @Override
    @Transactional
    public void saveNewBloodsToGroup(BloodBankDTO bloodBankDTO) {
        BloodTypes type = BloodTypes.valueOf(bloodBankDTO.getBloodType());

        Optional<BloodBank> optional = bloodBankRepo.findFirstByBloodType(type);

        if (optional.isPresent()) {
            BloodBank existing = optional.get();
            double newPoints = existing.getPoints() + bloodBankDTO.getPoints();
            existing.setPoints(newPoints);
            bloodBankRepo.save(existing);
        } else {
            BloodBank newBloodBank = modelMapper.map(bloodBankDTO, BloodBank.class);
            bloodBankRepo.save(newBloodBank);
        }
    }


    @Override
    @Transactional
    public void updateBloodGroupsDetails(BloodBankDTO bloodBankDTO) {
        if (!bloodBankRepo.existsById(bloodBankDTO.getBloodBankId())) {
            throw new RuntimeException("Blood Bank does not Exist");
        }
        bloodBankRepo.save(modelMapper.map(bloodBankDTO, BloodBank.class));
    }

    @Override
    @Transactional
    public void deleteBloodsFromGroups(Integer bloodBankId) {
        bloodBankRepo.deleteById(bloodBankId);
    }

    @Override
    @Transactional
    public void addBloodPoints(String bloodType, Double points) {
        BloodBank bloodBank = bloodBankRepo.findFirstByBloodType(BloodTypes.valueOf(bloodType))
                .orElseGet(() -> {
                    BloodBank newBank = new BloodBank();
                    newBank.setBloodType(BloodTypes.valueOf(bloodType));
                    newBank.setPoints(0.0);
                    return bloodBankRepo.save(newBank);
                });

        bloodBank.setPoints(bloodBank.getPoints() + points);
        bloodBankRepo.save(bloodBank);
    }

    public double getBloodStock(){
        return bloodBankRepo.getTotalBloodStock();
    }

    public List<BloodBankDTO> searchByBloodType(String bloodType) {
        BloodTypes type;
        try {
            type = BloodTypes.valueOf(bloodType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }

        return bloodBankRepo.findByBloodType(type)
                .stream()
                .map(bloodBank -> modelMapper.map(bloodBank, BloodBankDTO.class))
                .collect(Collectors.toList());
    }

}
































