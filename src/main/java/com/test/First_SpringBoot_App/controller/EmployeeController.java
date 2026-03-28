package com.test.First_SpringBoot_App.controller;

import com.test.First_SpringBoot_App.dto.EmployeeDto;
import com.test.First_SpringBoot_App.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // http://localhost:8085/api/employees/create

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        // Call the service layer to create the employee
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(createdEmployee);
    }

    // http://localhost:8085/api/employees/
    @GetMapping("/findById/{id}")
    public ResponseEntity<EmployeeDto> findEmployeebyId(@PathVariable Long id) {
        EmployeeDto employeeById = employeeService.findById(id);
        return ResponseEntity.ok(employeeById);
    }

    // http://localhost:8085/api/employees/getAllEmployees
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllE() {
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    // http://localhost:8085/api/employees/findByCity
    @GetMapping("/findByCity/{city}")
    public ResponseEntity<List<EmployeeDto>> findByCity(@PathVariable String city) {
        List<EmployeeDto> allEmployees = employeeService.findByCity(city);
        return ResponseEntity.ok(allEmployees);
    }


}
