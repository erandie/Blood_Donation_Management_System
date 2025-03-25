package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Funds;
import org.example.blood_donation_api.Repo.FundsRepo;
import org.example.blood_donation_api.Service.FundsService;
import org.example.blood_donation_api.dto.FundsDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FundsServiceImpl implements FundsService {

    @Autowired
    private FundsRepo fundsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FundsDTO> getAllFunds(){
        List<Funds> fundsList = fundsRepo.findAll();
        return modelMapper.map(fundsList, new TypeToken<List<FundsDTO>>(){}.getType());
    }

    @Override
    public FundsDTO saveFunds(FundsDTO fundsDTO) {
        fundsRepo.save(modelMapper.map(fundsDTO, Funds.class));
        return fundsDTO;
    }

    @Override
    public FundsDTO updateFunds(FundsDTO fundsDTO) {
        fundsRepo.save(modelMapper.map(fundsDTO, Funds.class));
        return fundsDTO;
    }

    @Override
    public String deleteFunds(Integer fundId) {
        fundsRepo.deleteById(fundId);
        return "Fund Details Delete!";
    }



}





























