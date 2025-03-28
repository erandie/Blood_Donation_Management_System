package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.BloodBankDTO;

import java.util.List;

public interface BloodBankService {

    List<BloodBankDTO> getAllBloodsInGroup();
    void saveNewBloodsToGroup(BloodBankDTO bloodBankDTO);
    void updateBloodGroupsDetails(BloodBankDTO bloodBankDTO);
    void deleteBloodsFromGroups(Integer bloodBankId);
}
