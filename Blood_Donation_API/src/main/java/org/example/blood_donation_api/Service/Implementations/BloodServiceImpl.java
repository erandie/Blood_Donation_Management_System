package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Blood;
import org.example.blood_donation_api.Repo.BloodRepo;
import org.example.blood_donation_api.Service.BloodService;
import org.example.blood_donation_api.dto.BloodDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BloodServiceImpl implements BloodService {

    @Autowired
    private BloodRepo bloodRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BloodDTO> getAllBloodDetails() {
        List<Blood> bloodList = bloodRepo.findAll();
        return modelMapper.map(bloodList, new TypeToken<List<BloodDTO>>() {
        }.getType());
    }

    @Override
    public void saveBloods(BloodDTO bloodDTO) {
        if (bloodRepo.existsById(bloodDTO.getBlood_id())) {
            throw new RuntimeException("Blood Already Exists");
        }

        bloodRepo.save(modelMapper.map(bloodDTO, Blood.class));
    }

    @Override
    public void updateBloods(BloodDTO bloodDTO) {
        if (!bloodRepo.existsById(bloodDTO.getBlood_id())) {
            throw new RuntimeException("Blood does not Exist");
        }
        bloodRepo.save(modelMapper.map(bloodDTO, Blood.class));
    }


    @Override
    public void deleteBloods(Integer Blood_id) {
        bloodRepo.deleteById(Blood_id);
    }
}
