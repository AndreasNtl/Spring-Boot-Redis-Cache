package com.example.redis.springbootrediscache.controller;


import com.example.redis.springbootrediscache.dto.CreateEmployeeRequest;
import com.example.redis.springbootrediscache.dto.UpdateEmployeeRequest;
import com.example.redis.springbootrediscache.model.Employee;
import com.example.redis.springbootrediscache.service.EmployeeService;
import com.example.redis.springbootrediscache.validations.ResourceNotFoundException;
import com.example.redis.springbootrediscache.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee addEmployee(@Valid @RequestBody CreateEmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{employeeId}")
    @Cacheable(value = "employees", key = "#employeeId")
    public Employee findEmployeeById(@PathVariable(value = "employeeId") Integer employeeId) {
        log.debug("Employee fetching from database: " + employeeId);
        return employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found " + employeeId));
    }


    @PutMapping("/{employeeId}")
    @CachePut(value = "employees", key = "#employeeId")
    public Employee updateEmployee(@PathVariable(value = "employeeId") Integer employeeId,
                                      @Valid @RequestBody UpdateEmployeeRequest employeeDtoDetails) {
        return employeeService.updateEmployee(employeeId, employeeDtoDetails);

    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        if(!employeeRepository.existsById(employeeId))
            throw new ResourceNotFoundException("Employee not found " + employeeId);
        employeeRepository.deleteById(employeeId);
    }
}
