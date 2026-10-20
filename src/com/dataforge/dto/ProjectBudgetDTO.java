package com.dataforge.dto;

import java.math.BigDecimal;

/**
 * DTO for Q8: Project Name, Department Name, and Budget.
 */
public class ProjectBudgetDTO {
    private String projectName;
    private String deptName;
    private BigDecimal budget;

    public ProjectBudgetDTO() {}

    public ProjectBudgetDTO(String projectName, String deptName, BigDecimal budget) {
        this.projectName = projectName;
        this.deptName = deptName;
        this.budget = budget;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
