package com.dataforge.model;

import java.math.BigDecimal;

/**
 * Domain entity representing a Project.
 */
public class Project {
    private int projectId;
    private String projectName;
    private int deptId;
    private BigDecimal budget;

    public Project() {
    }

    public Project(int projectId, String projectName, int deptId, BigDecimal budget) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.deptId = deptId;
        this.budget = budget;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", deptId=" + deptId +
                ", budget=" + budget +
                '}';
    }
}
