package com.skb.service;

import com.skb.entity.Employee;
import com.skb.exception.ResourceNotFound;
import com.skb.payload.EmployeeDto;
import com.skb.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto addEmployee(EmployeeDto dto){
        Employee employee = mapToEntity(dto);
        Employee emp= employeeRepository.save(employee);
        EmployeeDto empdto = mapToDto(emp);
        return empdto;
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updateEmp = employeeRepository.save(employee);
        EmployeeDto empdto = mapToDto(updateEmp);
        return empdto;
    }
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> empdto = employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return empdto;
    }
    EmployeeDto mapToDto(Employee employee){
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(dto.getMobile());
        EmployeeDto dto = modelMapper.map(employee,EmployeeDto.class);
        return dto;
    }
    Employee mapToEntity(EmployeeDto dto){
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        Employee emp = modelMapper.map(dto,Employee.class);
        return emp;
    }
    public EmployeeDto getEmployeeById(long id){
//        Optional<Employee> emp = employeeRepository.findById(id);
//        Employee employee = emp.get();
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("no such employee id is present"));
        return mapToDto(employee);
    }

}
