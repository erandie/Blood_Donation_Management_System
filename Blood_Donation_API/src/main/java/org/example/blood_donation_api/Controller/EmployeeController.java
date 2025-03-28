package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.EmployeeServiceImpl;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("get")
    public List<EmployeeDTO> getAllEmployees(){
       return employeeService.getAllEmployees();

    }

    @PostMapping("save")
    public ResponseUtil addEmployees(@RequestBody EmployeeDTO employeeDTO){
        employeeService.saveEmployee(employeeDTO);
        return new ResponseUtil(
                201,
                "Employee Saved!",
                employeeDTO
        );
    }

    @PutMapping("update")
    public ResponseUtil updateEmployees(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeDTO);
        return new ResponseUtil(
                200,
                "Employee Updated!",
                employeeDTO
        );
    }

    @DeleteMapping("delete/{empId}")
    public ResponseUtil deleteEmployee(@PathVariable Integer empId) {
        employeeService.deleteEmployee(empId);
        return new ResponseUtil(
                200,
                "Employee Deleted!",
                null
        );
    }

}
































