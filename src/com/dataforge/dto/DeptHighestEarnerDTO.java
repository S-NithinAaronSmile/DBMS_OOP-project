package com.dataforge.dto;

import java.math.BigDecimal;

/**
 * DTO for Q14: Department Name, Highest-Paid Employee Name, and Salary.
 */
public class DeptHighestEarnerDTO {
    private String deptName;
    private String name;
    private BigDecimal salary;

    public DeptHighestEarnerDTO() {}

    public DeptHighestEarnerDTO(String deptName, String name, BigDecimal salary) {
        this.deptName = deptName;
        this.name = name;
        this.salary = salary;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
