package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.ReceptionistServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
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
    public ResponseUtil saveReceptionist(@RequestBody ReceptionistDTO receptionistDTO){
        receptionistService.saveReceptionist(receptionistDTO);
        return new ResponseUtil(
                201,
                "Receptionist Added!",
                receptionistDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateReceptionists(@RequestBody ReceptionistDTO receptionistDTO) {
        receptionistService.updateReceptionist(receptionistDTO);
        return new ResponseUtil(
                200,
                "Receptionist Updated!",
                receptionistDTO
        );
    }

    @DeleteMapping("delete/{receptionistId}")
    public ResponseUtil deleteReceptionist(@PathVariable Integer receptionistId) {
        receptionistService.deleteReceptionist(receptionistId);
        return new ResponseUtil(
                200,
                "Receptionist Deleted!",
                null
        );
    }

}
