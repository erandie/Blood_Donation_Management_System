package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.BloodDTO;

import java.util.List;

public interface BloodService {
    List<BloodDTO> getAllBloodDetails();
    BloodDTO saveBloods(BloodDTO bloodDTO);
    BloodDTO updateBloods(BloodDTO bloodDTO);
    String deleteBloods(Integer Blood_id);

}
