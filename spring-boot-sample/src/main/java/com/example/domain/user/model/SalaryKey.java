package com.example.domain.user.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SalaryKey implements Serializable {
    private String userId;
    private String yearMonth;
}
