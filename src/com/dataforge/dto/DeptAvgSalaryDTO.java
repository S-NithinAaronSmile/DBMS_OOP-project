package com.dataforge.dto;

import java.math.BigDecimal;

/**
 * DTO for Q13: Department Name and Average Salary.
 */
public class DeptAvgSalaryDTO {
    private String deptName;
    private BigDecimal avgSalary;

    public DeptAvgSalaryDTO() {}

    public DeptAvgSalaryDTO(String deptName, BigDecimal avgSalary) {
        this.deptName = deptName;
        this.avgSalary = avgSalary;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BigDecimal getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(BigDecimal avgSalary) {
        this.avgSalary = avgSalary;
    }
}
