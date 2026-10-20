package com.dataforge.dto;

/**
 * DTO for Q10: Employee and Manager Hierarchy.
 */
public class EmployeeManagerDTO {
    private String employee;
    private String manager;

    public EmployeeManagerDTO() {}

    public EmployeeManagerDTO(String employee, String manager) {
        this.employee = employee;
        this.manager = manager;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
