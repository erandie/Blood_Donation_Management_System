package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.FundsDTO;

import java.util.List;

public interface FundsService {
    List<FundsDTO> getAllFunds();
    FundsDTO saveFunds(FundsDTO fundsDTO);
    FundsDTO updateFunds(FundsDTO fundsDTO);
    String deleteFunds(Integer fundId);
}
