package com.skb.controller;


import com.skb.entity.Employee;
import com.skb.payload.EmployeeDto;
import com.skb.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto){
        EmployeeDto empdto = employeeService.addEmployee(dto);
        empdto.setDate(new Date());
        return new ResponseEntity<>(empdto, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted employee successfully",HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam long id, @RequestBody EmployeeDto dto){
        EmployeeDto emdto = employeeService.updateEmployee(id,dto);
        return new ResponseEntity<>(emdto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long empId){
        EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
