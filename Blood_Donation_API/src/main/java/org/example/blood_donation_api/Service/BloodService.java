package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.BloodDTO;

import java.util.List;

public interface BloodService {
    List<BloodDTO> getAllBloodDetails();
    void saveBloods(BloodDTO bloodDTO);
    void updateBloods(BloodDTO bloodDTO);
    void deleteBloods(Integer Blood_id);

}
