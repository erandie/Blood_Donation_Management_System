package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.BloodDTO;

import java.util.List;

public interface BloodService {
    public void addBloods(BloodDTO bloodDTO);

    public List<BloodDTO> getAllBloods();

    public List<BloodDTO> updateBloods(int Blood_id, BloodDTO bloodDTO);

    public boolean deleteBloods(int Blood_id);
}
