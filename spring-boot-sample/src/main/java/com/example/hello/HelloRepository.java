package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class HelloRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findById(String id) {
        String query = "SELECT * FROM employee WHERE id=?";
        Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);
        return employee;
    }
}
