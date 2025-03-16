package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.ReceptionistServiceImpl;
import org.example.blood_donation_api.dto.ReceptionistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/receptionist")
@CrossOrigin
public class ReceptionistController {

    @Autowired
    private ReceptionistServiceImpl receptionistService;

    @GetMapping("get")
    public List<ReceptionistDTO> getALlReceptionist(){
        return receptionistService.getAllReceptionistDetails();
    }

    @PostMapping("save")
    public ReceptionistDTO saveReceptionist(@RequestBody ReceptionistDTO receptionistDTO){
        return receptionistService.saveReceptionist(receptionistDTO);
    }

    @PutMapping("update")
    public ReceptionistDTO updateReceptionists(@RequestBody ReceptionistDTO receptionistDTO) {
        return receptionistService.updateReceptionist(receptionistDTO);
    }

    @DeleteMapping("delete/{Receptionist_id}")
    public String deleteReceptionist(@PathVariable Integer Receptionist_id) {
        return receptionistService.deleteReceptionist(Receptionist_id);
    }

}
