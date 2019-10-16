package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class HelloResource {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private List<Employee> employeeList = new ArrayList<>();

    @PostMapping(path = "/", produces = {"application/json"})
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);
        return ResponseEntity.ok("Added:" + employee.getName());
    }

    @GetMapping(path = "/", produces = {"application/json"})
    public List<Employee> getEmployeeList(){
        return employeeList;
    }

    @DeleteMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeList.remove(id);
        return new ResponseEntity<>("Deleted ID: " + id ,HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}", produces = {"application/json"})
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeList.get(id);
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setAge(employee.getAge());
        updatedEmployee.setGender(employee.getGender());

        return updatedEmployee;
    }
}
