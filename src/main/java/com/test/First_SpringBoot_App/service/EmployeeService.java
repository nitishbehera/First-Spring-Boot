package com.test.First_SpringBoot_App.service;

import com.test.First_SpringBoot_App.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeDto findById(Long id);

    public List<EmployeeDto> getAllEmployees();

    public List<EmployeeDto> findByCity(String city);

//    public EmployeeDto findAllEmployee();
//    public EmployeeDto updateEmployee();
//    public void deleteEmployee();
}
