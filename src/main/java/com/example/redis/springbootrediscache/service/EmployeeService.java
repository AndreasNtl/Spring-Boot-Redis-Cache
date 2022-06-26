package com.example.redis.springbootrediscache.service;

import com.example.redis.springbootrediscache.dto.CreateEmployeeRequest;
import com.example.redis.springbootrediscache.dto.EmployeeDto;
import com.example.redis.springbootrediscache.dto.UpdateEmployeeRequest;
import com.example.redis.springbootrediscache.model.Employee;
import com.example.redis.springbootrediscache.repository.EmployeeRepository;
import com.example.redis.springbootrediscache.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(CreateEmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSurname(employeeRequest.getSurname());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setJobTitle(employeeRequest.getJobTitle());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, UpdateEmployeeRequest employeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + employeeId));
        Optional.ofNullable(employeeDto.getName()).ifPresent(employee::setName);
        Optional.ofNullable(employeeDto.getSurname()).ifPresent(employee::setSurname);
        Optional.ofNullable(employeeDto.getDateOfBirth()).ifPresent(employee::setDateOfBirth);
        Optional.ofNullable(employeeDto.getJobTitle()).ifPresent(employee::setJobTitle);
        return employeeRepository.save(employee);
    }
}
