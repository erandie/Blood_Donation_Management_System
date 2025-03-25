package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.BloodBankDTO;

import java.util.List;

public interface BloodBankService {

    List<BloodBankDTO> getAllBloodsInGroup();
    BloodBankDTO saveNewBloodsToGroup(BloodBankDTO bloodBankDTO);
    BloodBankDTO updateBloodGroupsDetails(BloodBankDTO bloodBankDTO);
    String deleteBloodsFromGroups(Integer bloodBankId);
}
