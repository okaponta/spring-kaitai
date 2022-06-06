package com.example.application.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserApplicationService {
    public Map<String, Integer> getGenderMap() {
        Map<String, Integer> genderMap = new LinkedHashMap<>();
        genderMap.put("男性", 1);
        genderMap.put("女性", 2);
        return genderMap;
    }
}
