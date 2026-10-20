package com.dataforge;

import com.dataforge.config.DatabaseConnection;
import com.dataforge.dao.ReportDAO;
import com.dataforge.dao.ReportDAOImpl;
import com.dataforge.dto.*;
import com.dataforge.model.Department;
import com.dataforge.model.Employee;
import com.dataforge.util.TableRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application console entry point for DATAFORGE Employee Management System.
 */
public class Main {

    private static final ReportDAO dao = new ReportDAOImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Quick connection check
        if (!DatabaseConnection.getInstance().testConnection()) {
            System.err.println("Cannot connect to MySQL hr_db. Please verify db.properties and ensure MySQL service is running.");
            return;
        }

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            System.out.println();
            switch (input) {
                case "1":
                    runQ1();
                    break;
                case "2":
                    runQ2();
                    break;
                case "3":
                    runQ3();
                    break;
                case "4":
                    runQ4();
                    break;
                case "5":
                    runQ5();
                    break;
                case "6":
                    runQ6();
                    break;
                case "7":
                    runQ7();
                    break;
                case "8":
                    runQ8();
                    break;
                case "9":
                    runQ9();
                    break;
                case "10":
                    runQ10();
                    break;
                case "11":
                    runQ11();
                    break;
                case "12":
                    runQ12();
                    break;
                case "13":
                    runQ13();
                    break;
                case "14":
                    runQ14();
                    break;
                case "15":
                    runQ15();
                    break;
                case "16":
                    runAllReports();
                    break;
                case "17":
                    resetDatabase();
                    break;
                case "0":
                    running = false;
                    System.out.println("Thank you for using DATAFORGE Employee Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Please choose an option from the menu.");
            }

            if (running) {
                System.out.println("\nPress [Enter] to return to menu...");
                scanner.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println("===============================================================================");
        System.out.println("                 DATAFORGE - EMPLOYEE MANAGEMENT SYSTEM (hr_db)               ");
        System.out.println("                 DBMS x OOP Java Course Project (Scenario 2)                   ");
        System.out.println("===============================================================================");
        System.out.println("  [EASY QUERIES (Q1 - Q8, 5 pts each)]");
        System.out.println("   1. Q1: List IT department employees (name, job_title)");
        System.out.println("   2. Q2: Employees earning > 60000 (highest salary first)");
        System.out.println("   3. Q3: Employees hired after 2021-01-01 (by hire date)");
        System.out.println("   4. Q4: Total employees, max, min, and average salary");
        System.out.println("   5. Q5: Departments located in Chennai");
        System.out.println("   6. Q6: Employees whose name starts with 'S'");
        System.out.println("   7. Q7: Employees who have no manager (Directors/Heads)");
        System.out.println("   8. Q8: Projects with budget > 700000 (largest first)");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("  [MEDIUM QUERIES (Q9 - Q15, 10 pts each)]");
        System.out.println("   9. Q9: Dept employee count & avg salary (includes empty depts)");
        System.out.println("  10. Q10: Employee name with manager name (Self Join)");
        System.out.println("  11. Q11: Total hours logged on each project (highest first)");
        System.out.println("  12. Q12: Employees not assigned to any project (Anti-Join)");
        System.out.println("  13. Q13: Departments with average salary > 60000 (HAVING clause)");
        System.out.println("  14. Q14: Highest-paid employee in each department (Subquery)");
        System.out.println("  15. Q15: Cross-department project assignments (Multi-table join)");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("  [SYSTEM UTILITIES]");
        System.out.println("  16. Run All 15 Reports Sequentially (Batch Faculty Demo)");
        System.out.println("  17. Reset / Reseed Database from sql/setup.sql");
        System.out.println("   0. Exit");
        System.out.println("===============================================================================");
    }

    public static void runQ1() {
        System.out.println("[Q1] Name and Job Title of Employees in IT Department");
        System.out.println("SQL Concept: INNER JOIN with WHERE filter and ORDER BY emp_id");
        List<EmployeeJobDTO> list = dao.getITDepartmentEmployees();
        List<String[]> rows = new ArrayList<>();
        for (EmployeeJobDTO dto : list) {
            rows.add(new String[]{dto.getName(), dto.getJobTitle()});
        }
        TableRenderer.render(new String[]{"name", "job_title"}, rows);
    }

    public static void runQ2() {
        System.out.println("[Q2] Employees Earning More than 60,000 (Highest First)");
        System.out.println("SQL Concept: WHERE salary > 60000 ORDER BY salary DESC");
        List<Employee> list = dao.getHighEarners(60000.0);
        List<String[]> rows = new ArrayList<>();
        for (Employee emp : list) {
            rows.add(new String[]{emp.getName(), String.format("%.2f", emp.getSalary())});
        }
        TableRenderer.render(new String[]{"name", "salary"}, rows);
    }

    public static void runQ3() {
        System.out.println("[Q3] Employees Hired After 2021-01-01 (In Order of Hire Date)");
        System.out.println("SQL Concept: DATE comparison ORDER BY hire_date ASC");
        List<Employee> list = dao.getEmployeesHiredAfter("2021-01-01");
        List<String[]> rows = new ArrayList<>();
        for (Employee emp : list) {
            rows.add(new String[]{emp.getName(), emp.getHireDate().toString()});
        }
        TableRenderer.render(new String[]{"name", "hire_date"}, rows);
    }

    public static void runQ4() {
        System.out.println("[Q4] Total Employees, Max, Min, and Average Salary");
        System.out.println("SQL Concept: Aggregate Functions (COUNT, MAX, MIN, ROUND(AVG(), 2))");
        SalaryStatsDTO stats = dao.getSalaryStats();
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{
                String.valueOf(stats.getTotalEmployees()),
                String.format("%.2f", stats.getMaxSalary()),
                String.format("%.2f", stats.getMinSalary()),
                String.format("%.2f", stats.getAvgSalary())
        });
        TableRenderer.render(new String[]{"total_employees", "max_salary", "min_salary", "avg_salary"}, rows);
    }

    public static void runQ5() {
        System.out.println("[Q5] Departments Located in Chennai");
        System.out.println("SQL Concept: Equality Filter on VARCHAR location");
        List<Department> list = dao.getDepartmentsByLocation("Chennai");
        List<String[]> rows = new ArrayList<>();
        for (Department d : list) {
            rows.add(new String[]{d.getDeptName(), d.getLocation()});
        }
        TableRenderer.render(new String[]{"dept_name", "location"}, rows);
    }

    public static void runQ6() {
        System.out.println("[Q6] Employees Whose Name Starts with 'S'");
        System.out.println("SQL Concept: String Pattern Matching (LIKE 'S%')");
        List<String> list = dao.getEmployeesStartingWith("S");
        List<String[]> rows = new ArrayList<>();
        for (String name : list) {
            rows.add(new String[]{name});
        }
        TableRenderer.render(new String[]{"name"}, rows);
    }

    public static void runQ7() {
        System.out.println("[Q7] Employees Who Have No Manager");
        System.out.println("SQL Concept: Nullability Check (IS NULL)");
        List<EmployeeJobDTO> list = dao.getUnmanagedEmployees();
        List<String[]> rows = new ArrayList<>();
        for (EmployeeJobDTO dto : list) {
            rows.add(new String[]{dto.getName(), dto.getJobTitle()});
        }
        TableRenderer.render(new String[]{"name", "job_title"}, rows);
    }

    public static void runQ8() {
        System.out.println("[Q8] Projects with Budget Above 700,000 (Largest First)");
        System.out.println("SQL Concept: Multi-table JOIN with WHERE and ORDER BY DESC");
        List<ProjectBudgetDTO> list = dao.getProjectsAboveBudget(700000.0);
        List<String[]> rows = new ArrayList<>();
        for (ProjectBudgetDTO dto : list) {
            rows.add(new String[]{dto.getProjectName(), dto.getDeptName(), String.format("%.2f", dto.getBudget())});
        }
        TableRenderer.render(new String[]{"project_name", "dept_name", "budget"}, rows);
    }

    public static void runQ9() {
        System.out.println("[Q9] Department Employee Count and Average Salary (Including Empty Depts)");
        System.out.println("SQL Concept: LEFT JOIN with GROUP BY (Preserving Legal dept with 0 count)");
        List<DeptSalaryStatsDTO> list = dao.getDeptEmployeeCountAndAvgSalary();
        List<String[]> rows = new ArrayList<>();
        for (DeptSalaryStatsDTO dto : list) {
            String avg = dto.getAvgSalary() != null ? String.format("%.2f", dto.getAvgSalary()) : "NULL";
            rows.add(new String[]{dto.getDeptName(), String.valueOf(dto.getEmpCount()), avg});
        }
        TableRenderer.render(new String[]{"dept_name", "emp_count", "avg_salary"}, rows);
    }

    public static void runQ10() {
        System.out.println("[Q10] Employee and Manager Hierarchy (Self Join)");
        System.out.println("SQL Concept: LEFT SELF JOIN on employees e LEFT JOIN employees m");
        List<EmployeeManagerDTO> list = dao.getEmployeeManagerHierarchy();
        List<String[]> rows = new ArrayList<>();
        for (EmployeeManagerDTO dto : list) {
            rows.add(new String[]{dto.getEmployee(), dto.getManager()});
        }
        TableRenderer.render(new String[]{"employee", "manager"}, rows);
    }

    public static void runQ11() {
        System.out.println("[Q11] Total Hours Logged on Each Project (Highest First)");
        System.out.println("SQL Concept: JOIN with SUM(hours) GROUP BY project ORDER BY DESC");
        List<ProjectHoursDTO> list = dao.getTotalHoursPerProject();
        List<String[]> rows = new ArrayList<>();
        for (ProjectHoursDTO dto : list) {
            rows.add(new String[]{dto.getProjectName(), String.valueOf(dto.getTotalHours())});
        }
        TableRenderer.render(new String[]{"project_name", "total_hours"}, rows);
    }

    public static void runQ12() {
        System.out.println("[Q12] Employees Who Are Not Assigned to Any Project");
        System.out.println("SQL Concept: Anti-Join (LEFT JOIN project_assignments WHERE assign_id IS NULL)");
        List<String> list = dao.getEmployeesWithoutProjects();
        List<String[]> rows = new ArrayList<>();
        for (String name : list) {
            rows.add(new String[]{name});
        }
        TableRenderer.render(new String[]{"name"}, rows);
    }

    public static void runQ13() {
        System.out.println("[Q13] Departments Whose Average Salary is Above 60,000");
        System.out.println("SQL Concept: GROUP BY with HAVING AVG(salary) > 60000");
        List<DeptAvgSalaryDTO> list = dao.getDepartmentsWithAvgSalaryAbove(60000.0);
        List<String[]> rows = new ArrayList<>();
        for (DeptAvgSalaryDTO dto : list) {
            rows.add(new String[]{dto.getDeptName(), String.format("%.6f", dto.getAvgSalary())});
        }
        TableRenderer.render(new String[]{"dept_name", "avg_salary"}, rows);
    }

    public static void runQ14() {
        System.out.println("[Q14] Highest-Paid Employee in Each Department");
        System.out.println("SQL Concept: Correlated Subquery WHERE salary = (SELECT MAX(salary)...)");
        List<DeptHighestEarnerDTO> list = dao.getHighestPaidEmployeePerDept();
        List<String[]> rows = new ArrayList<>();
        for (DeptHighestEarnerDTO dto : list) {
            rows.add(new String[]{dto.getDeptName(), dto.getName(), String.format("%.2f", dto.getSalary())});
        }
        TableRenderer.render(new String[]{"dept_name", "name", "salary"}, rows);
    }

    public static void runQ15() {
        System.out.println("[Q15] Employees Working on Cross-Department Projects");
        System.out.println("SQL Concept: 4-Table Join with Inequality Filter (e.dept_id <> p.dept_id)");
        List<CrossDeptAssignmentDTO> list = dao.getCrossDepartmentAssignments();
        List<String[]> rows = new ArrayList<>();
        for (CrossDeptAssignmentDTO dto : list) {
            rows.add(new String[]{dto.getEmployee(), dto.getHomeDept(), dto.getProjectName(), dto.getProjectDept()});
        }
        TableRenderer.render(new String[]{"employee", "home_dept", "project_name", "project_dept"}, rows);
    }

    public static void runAllReports() {
        System.out.println("===============================================================================");
        System.out.println("               BATCH DEMO: EXECUTING ALL 15 OFFICIAL REPORTS                   ");
        System.out.println("===============================================================================\n");

        runQ1(); System.out.println();
        runQ2(); System.out.println();
        runQ3(); System.out.println();
        runQ4(); System.out.println();
        runQ5(); System.out.println();
        runQ6(); System.out.println();
        runQ7(); System.out.println();
        runQ8(); System.out.println();
        runQ9(); System.out.println();
        runQ10(); System.out.println();
        runQ11(); System.out.println();
        runQ12(); System.out.println();
        runQ13(); System.out.println();
        runQ14(); System.out.println();
        runQ15(); System.out.println();

        System.out.println(">>> All 15 Reports Generated Successfully! <<<");
    }

    private static void resetDatabase() {
        System.out.print("Are you sure you want to re-seed hr_db from sql/setup.sql? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            boolean ok = dao.resetDatabase();
            if (ok) {
                System.out.println("Database reset and re-seeded successfully!");
            } else {
                System.out.println("Failed to reset database.");
            }
        } else {
            System.out.println("Reset cancelled.");
        }
    }
}
