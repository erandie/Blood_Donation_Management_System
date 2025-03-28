package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.FundsServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
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
    public ResponseUtil saveFunds(@RequestBody FundsDTO fundsDTO) {
        fundsService.saveFunds(fundsDTO);
        return new ResponseUtil(
                201,
                "Fund Added!",
                fundsDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateFunds(@RequestBody FundsDTO fundsDTO) {
        fundsService.updateFunds(fundsDTO);
        return new ResponseUtil(
                200,
                "Fund Updated!",
                fundsDTO
        );
    }

    @DeleteMapping("delete/{fundId}")
    public ResponseUtil deleteFunds(@PathVariable Integer fundId){
        fundsService.deleteFunds(fundId);
        return new ResponseUtil(
                200,
                "fund Deleted!",
                null
        );
    }

}
