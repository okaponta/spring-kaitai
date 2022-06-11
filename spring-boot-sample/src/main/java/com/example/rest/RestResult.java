package com.example.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult {
    // リターンコード
    private int result;

    private Map<String, String> errors;
}
