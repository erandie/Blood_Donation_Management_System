package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.FundsServiceImpl;
import org.example.blood_donation_api.dto.FundsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/funds")
@CrossOrigin
public class FundsController {

    @Autowired
    private FundsServiceImpl fundsService;

    @GetMapping("get")
    public List<FundsDTO> getAllFuds(){
       return fundsService.getAllFunds();
    }

    @PostMapping("save")
    public FundsDTO saveFunds(@RequestBody FundsDTO fundsDTO) {
        return fundsService.saveFunds(fundsDTO);
    }

    @PutMapping("update")
    public FundsDTO updateFunds(@RequestBody FundsDTO fundsDTO) {
        return fundsService.updateFunds(fundsDTO);
    }

    @DeleteMapping("delete/{fundId}")
    public String deleteFunds(@PathVariable Integer fundId){
        return fundsService.deleteFunds(fundId);
    }

}
