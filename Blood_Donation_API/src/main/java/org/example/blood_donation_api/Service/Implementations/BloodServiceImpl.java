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
/*public class BloodServiceImpl implements BloodService {*/
@Transactional
public class BloodServiceImpl{

    @Autowired
    private BloodRepo bloodRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<BloodDTO> getAllBloodDetails(){
        List<Blood> bloodList = bloodRepo.findAll();
        return modelMapper.map(bloodList, new TypeToken<List<BloodDTO>>(){}.getType());
    }

    public BloodDTO saveBloods(BloodDTO bloodDTO){
        bloodRepo.save(modelMapper.map(bloodDTO, Blood.class));
        return bloodDTO;
    }

    public BloodDTO updateBloods(BloodDTO bloodDTO){
        bloodRepo.save(modelMapper.map(bloodDTO, Blood.class));
        return bloodDTO;
    }

    public String deleteBloods(Integer Blood_id){
        bloodRepo.deleteById(Blood_id);
        return "Blood Details Deleted!";
    }

    /*@Override
    public void addBloods(BloodDTO bloodDTO) {
    }

    @Override
    public List<BloodDTO> getAllBloods() {
        return null;
    }

    @Override
    public List<BloodDTO> updateBloods(int Blood_id, BloodDTO bloodDTO) {
        return null;
    }

    @Override
    public boolean deleteBloods(int Blood_id) {
        return false;
    }*/
}
