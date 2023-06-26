package com.example.departmentservice.controller;

import com.example.departmentservice.client.EmployeeClient;
import com.example.departmentservice.model.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping()
    public  Department add(@RequestBody Department department){
       return departmentRepository.addDepartment(department);

    }

    @GetMapping
    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public  Department findById(@PathVariable  Long id)
    {
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {

        List<Department> departments
                = departmentRepository.findAll();
        System.out.println(departments);
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return  departments;
    }



}
