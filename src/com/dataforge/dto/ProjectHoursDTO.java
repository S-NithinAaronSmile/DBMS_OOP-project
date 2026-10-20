package com.dataforge.dto;

/**
 * DTO for Q11: Project Name and Total Hours Logged.
 */
public class ProjectHoursDTO {
    private String projectName;
    private long totalHours;

    public ProjectHoursDTO() {}

    public ProjectHoursDTO(String projectName, long totalHours) {
        this.projectName = projectName;
        this.totalHours = totalHours;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(long totalHours) {
        this.totalHours = totalHours;
    }
}
