package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.ReceptionistServiceImpl;
import org.example.blood_donation_api.dto.ReceptionistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/receptionist")
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

    @DeleteMapping("delete/{receptionistId}")
    public String deleteReceptionist(@PathVariable Integer receptionistId) {
        return receptionistService.deleteReceptionist(receptionistId);
    }

}
