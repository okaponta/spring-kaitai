package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelloService {

    @Autowired
    private HelloRepository repository;

    public Employee getEmployee(String id){
        Map<String, Object> map = repository.findById(id);

        String employeeId = (String)map.get("id");
        String name = (String)map.get("name");
        int age = (Integer)map.get("age");

        Employee employee = new Employee(employeeId,name,age);
        return employee;
    }
}
