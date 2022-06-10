package com.example.form;

import com.example.domain.user.model.Department;
import com.example.domain.user.model.Salary;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public final class UserDetailForm {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Department department;
    private List<Salary> salaryList;
}
