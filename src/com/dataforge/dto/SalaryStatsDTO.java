package com.dataforge.dto;

import java.math.BigDecimal;

/**
 * DTO for Q4: Aggregated Salary Statistics.
 */
public class SalaryStatsDTO {
    private int totalEmployees;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;
    private BigDecimal avgSalary;

    public SalaryStatsDTO() {}

    public SalaryStatsDTO(int totalEmployees, BigDecimal maxSalary, BigDecimal minSalary, BigDecimal avgSalary) {
        this.totalEmployees = totalEmployees;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.avgSalary = avgSalary;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(BigDecimal avgSalary) {
        this.avgSalary = avgSalary;
    }
}
