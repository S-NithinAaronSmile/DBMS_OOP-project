package com.dataforge.dao;

import com.dataforge.dto.*;
import com.dataforge.model.Department;
import com.dataforge.model.Employee;

import java.util.List;

/**
 * Data Access Object (DAO) Interface defining query operations for all 15 Scenario 2 questions.
 */
public interface ReportDAO {

    // Q1 (Easy - 5 pts): Name and job title of all employees in the IT department
    List<EmployeeJobDTO> getITDepartmentEmployees();

    // Q2 (Easy - 5 pts): Employees earning more than 60,000, highest salary first
    List<Employee> getHighEarners(double threshold);

    // Q3 (Easy - 5 pts): Employees hired after 2021-01-01 in order of hire date
    List<Employee> getEmployeesHiredAfter(String date);

    // Q4 (Easy - 5 pts): Total employees, max salary, min salary, and average salary
    SalaryStatsDTO getSalaryStats();

    // Q5 (Easy - 5 pts): Departments located in Chennai
    List<Department> getDepartmentsByLocation(String location);

    // Q6 (Easy - 5 pts): Employees whose name starts with 'S'
    List<String> getEmployeesStartingWith(String prefix);

    // Q7 (Easy - 5 pts): Employees who have no manager
    List<EmployeeJobDTO> getUnmanagedEmployees();

    // Q8 (Easy - 5 pts): Projects with budget above 700,000, largest first
    List<ProjectBudgetDTO> getProjectsAboveBudget(double threshold);

    // Q9 (Medium - 10 pts): Dept employee count and avg salary (empty depts included)
    List<DeptSalaryStatsDTO> getDeptEmployeeCountAndAvgSalary();

    // Q10 (Medium - 10 pts): Employee name with manager name (NULL if none)
    List<EmployeeManagerDTO> getEmployeeManagerHierarchy();

    // Q11 (Medium - 10 pts): Total hours logged on each project, highest first
    List<ProjectHoursDTO> getTotalHoursPerProject();

    // Q12 (Medium - 10 pts): Employees who are not assigned to any project
    List<String> getEmployeesWithoutProjects();

    // Q13 (Medium - 10 pts): Departments whose average salary is above 60,000
    List<DeptAvgSalaryDTO> getDepartmentsWithAvgSalaryAbove(double threshold);

    // Q14 (Medium - 10 pts): Highest-paid employee in each department
    List<DeptHighestEarnerDTO> getHighestPaidEmployeePerDept();

    // Q15 (Medium - 10 pts): Employees working on projects belonging to a different department
    List<CrossDeptAssignmentDTO> getCrossDepartmentAssignments();

    // Utility: Reset and reseed the database from sql/setup.sql
    boolean resetDatabase();
}
