package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Receptionist;
import org.example.blood_donation_api.Repo.ReceptionistRepo;
import org.example.blood_donation_api.dto.ReceptionistDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReceptionistServiceImpl {
    @Autowired
    private ReceptionistRepo receptionistRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReceptionistDTO> getAllReceptionistDetails(){
        List<Receptionist> receptionistList = receptionistRepo.findAll();
        return modelMapper.map(receptionistList, new TypeToken<List<ReceptionistDTO>>(){}.getType());
    }

    public ReceptionistDTO saveReceptionist(ReceptionistDTO receptionistDTO) {
        receptionistRepo.save(modelMapper.map(receptionistDTO, Receptionist.class));
        return receptionistDTO;
    }

    public ReceptionistDTO updateReceptionist(ReceptionistDTO receptionistDTO){
        receptionistRepo.save(modelMapper.map(receptionistDTO, Receptionist.class));
        return receptionistDTO;
    }

    public String deleteReceptionist(Integer receptionistId) {
        receptionistRepo.deleteById(receptionistId);
        return "Receptionist Details Deleted!";
    }
}


















