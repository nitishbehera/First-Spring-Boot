package com.test.First_SpringBoot_App.service.impl;

import com.test.First_SpringBoot_App.dto.EmployeeDto;
import com.test.First_SpringBoot_App.entity.Employee;
import com.test.First_SpringBoot_App.repository.EmployeeRepository;
import com.test.First_SpringBoot_App.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = convertToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto1 = convertToDto(savedEmployee);
        return employeeDto1;
    }
    private static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setCity(employee.getCity());
        return employeeDto;
    }

    private static Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setSalary(employeeDto.getSalary());
        employee.setCity(employeeDto.getCity());
        return employee;
    }
}
