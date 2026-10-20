package com.dataforge.model;

/**
 * Domain entity representing a Project Assignment.
 */
public class ProjectAssignment {
    private int assignId;
    private int empId;
    private int projectId;
    private int hours;

    public ProjectAssignment() {
    }

    public ProjectAssignment(int assignId, int empId, int projectId, int hours) {
        this.assignId = assignId;
        this.empId = empId;
        this.projectId = projectId;
        this.hours = hours;
    }

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "ProjectAssignment{" +
                "assignId=" + assignId +
                ", empId=" + empId +
                ", projectId=" + projectId +
                ", hours=" + hours +
                '}';
    }
}
