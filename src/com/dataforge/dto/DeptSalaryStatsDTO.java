package com.dataforge.dto;

import java.math.BigDecimal;

/**
 * DTO for Q9: Department Name, Employee Count, and Average Salary.
 */
public class DeptSalaryStatsDTO {
    private String deptName;
    private int empCount;
    private BigDecimal avgSalary;

    public DeptSalaryStatsDTO() {}

    public DeptSalaryStatsDTO(String deptName, int empCount, BigDecimal avgSalary) {
        this.deptName = deptName;
        this.empCount = empCount;
        this.avgSalary = avgSalary;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getEmpCount() {
        return empCount;
    }

    public void setEmpCount(int empCount) {
        this.empCount = empCount;
    }

    public BigDecimal getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(BigDecimal avgSalary) {
        this.avgSalary = avgSalary;
    }
}
