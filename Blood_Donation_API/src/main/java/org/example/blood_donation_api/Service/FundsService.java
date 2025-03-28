package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.FundsDTO;

import java.util.List;

public interface FundsService {
    List<FundsDTO> getAllFunds();
    void saveFunds(FundsDTO fundsDTO);
    void updateFunds(FundsDTO fundsDTO);
    void deleteFunds(Integer fundId);
}
