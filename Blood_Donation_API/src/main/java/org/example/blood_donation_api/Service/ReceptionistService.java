package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.ReceptionistDTO;

import java.util.List;

public interface ReceptionistService {
    List<ReceptionistDTO> getAllReceptionistDetails();
    void saveReceptionist(ReceptionistDTO receptionistDTO);
    void updateReceptionist(ReceptionistDTO receptionistDTO);
    void deleteReceptionist(Integer receptionistId);
}
