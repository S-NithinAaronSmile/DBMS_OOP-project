package com.dataforge.dto;

/**
 * DTO for Q15: Cross-Department Project Assignment.
 */
public class CrossDeptAssignmentDTO {
    private String employee;
    private String homeDept;
    private String projectName;
    private String projectDept;

    public CrossDeptAssignmentDTO() {}

    public CrossDeptAssignmentDTO(String employee, String homeDept, String projectName, String projectDept) {
        this.employee = employee;
        this.homeDept = homeDept;
        this.projectName = projectName;
        this.projectDept = projectDept;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getHomeDept() {
        return homeDept;
    }

    public void setHomeDept(String homeDept) {
        this.homeDept = homeDept;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDept() {
        return projectDept;
    }

    public void setProjectDept(String projectDept) {
        this.projectDept = projectDept;
    }
}
