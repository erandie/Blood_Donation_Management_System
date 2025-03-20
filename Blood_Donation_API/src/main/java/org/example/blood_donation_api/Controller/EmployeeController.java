package org.example.blood_donation_api.Controller;

import org.example.blood_donation_api.Service.Implementations.EmployeeServiceImpl;
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
    public EmployeeDTO addEmployees(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.saveEmployee(employeeDTO);
    }

    @PutMapping("update")
    public EmployeeDTO updateEmployees(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("delete/{empId}")
    public String deleteEmployee(@PathVariable Integer empId) {
        return employeeService.deleteEmployee(empId);
    }

}
































