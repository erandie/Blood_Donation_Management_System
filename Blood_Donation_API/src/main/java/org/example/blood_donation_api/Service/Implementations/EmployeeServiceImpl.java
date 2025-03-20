package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Employee;
import org.example.blood_donation_api.Repo.EmployeeRepo;
import org.example.blood_donation_api.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
        return employeeDTO;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
        return employeeDTO;
    }

    public String deleteEmployee(Integer empId) {
        employeeRepo.deleteById(empId);
        return "Employee Details Deleted!";
    }

}




























