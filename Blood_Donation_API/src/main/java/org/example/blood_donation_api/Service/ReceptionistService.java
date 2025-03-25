package org.example.blood_donation_api.Service;

import org.example.blood_donation_api.dto.ReceptionistDTO;

import java.util.List;

public interface ReceptionistService {
    List<ReceptionistDTO> getAllReceptionistDetails();
    ReceptionistDTO saveReceptionist(ReceptionistDTO receptionistDTO);
    ReceptionistDTO updateReceptionist(ReceptionistDTO receptionistDTO);
    String deleteReceptionist(Integer receptionistId);
}
