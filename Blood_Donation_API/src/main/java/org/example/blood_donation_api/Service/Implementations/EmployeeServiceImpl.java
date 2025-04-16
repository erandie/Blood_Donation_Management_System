package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Employee;
import org.example.blood_donation_api.Repo.EmployeeRepo;
import org.example.blood_donation_api.Service.EmployeeService;
import org.example.blood_donation_api.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepo.existsById(employeeDTO.getEmpId())){
            throw new RuntimeException("Employee Already Exists");
        }
        employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        if (!employeeRepo.existsById(employeeDTO.getEmpId())) {
            throw new RuntimeException("Employee does not Exist");
        }
        employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
    }


    @Override
    public void deleteEmployee(Integer empId) {
        employeeRepo.deleteById(empId);
    }

    public List<EmployeeDTO> searchByName(String name){
        return employeeRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

}




























