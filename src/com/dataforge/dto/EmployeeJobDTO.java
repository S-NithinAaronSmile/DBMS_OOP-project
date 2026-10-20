package com.dataforge.dto;

/**
 * DTO for Q1: Employee Name and Job Title.
 */
public class EmployeeJobDTO {
    private String name;
    private String jobTitle;

    public EmployeeJobDTO() {}

    public EmployeeJobDTO(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
