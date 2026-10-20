package com.dataforge.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Domain entity representing an Employee.
 */
public class Employee {
    private int empId;
    private String name;
    private Integer deptId;
    private Integer managerId;
    private BigDecimal salary;
    private Date hireDate;
    private String jobTitle;

    public Employee() {
    }

    public Employee(int empId, String name, Integer deptId, Integer managerId, BigDecimal salary, Date hireDate, String jobTitle) {
        this.empId = empId;
        this.name = name;
        this.deptId = deptId;
        this.managerId = managerId;
        this.salary = salary;
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", deptId=" + deptId +
                ", managerId=" + managerId +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
