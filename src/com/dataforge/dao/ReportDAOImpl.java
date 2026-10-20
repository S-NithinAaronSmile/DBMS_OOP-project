package com.dataforge.dao;

import com.dataforge.config.DatabaseConnection;
import com.dataforge.dto.*;
import com.dataforge.model.Department;
import com.dataforge.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC Implementation of ReportDAO.
 * Implements all 15 queries adhering strictly to the official Faculty Solution Key.
 */
public class ReportDAOImpl implements ReportDAO {

    private final DatabaseConnection dbManager;

    public ReportDAOImpl() {
        this.dbManager = DatabaseConnection.getInstance();
    }

    @Override
    public List<EmployeeJobDTO> getITDepartmentEmployees() {
        List<EmployeeJobDTO> list = new ArrayList<>();
        String sql = "SELECT e.name, e.job_title " +
                     "FROM employees e JOIN departments d ON e.dept_id = d.dept_id " +
                     "WHERE d.dept_name = 'IT' " +
                     "ORDER BY e.emp_id";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new EmployeeJobDTO(rs.getString("name"), rs.getString("job_title")));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q1: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Employee> getHighEarners(double threshold) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT name, salary FROM employees WHERE salary > ? ORDER BY salary DESC";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, threshold);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.setName(rs.getString("name"));
                    emp.setSalary(rs.getBigDecimal("salary"));
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q2: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Employee> getEmployeesHiredAfter(String date) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT name, hire_date FROM employees WHERE hire_date > ? ORDER BY hire_date";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.setName(rs.getString("name"));
                    emp.setHireDate(rs.getDate("hire_date"));
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q3: " + e.getMessage());
        }
        return list;
    }

    @Override
    public SalaryStatsDTO getSalaryStats() {
        String sql = "SELECT COUNT(*) AS total_employees, MAX(salary) AS max_salary, " +
                     "MIN(salary) AS min_salary, ROUND(AVG(salary), 2) AS avg_salary " +
                     "FROM employees";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return new SalaryStatsDTO(
                        rs.getInt("total_employees"),
                        rs.getBigDecimal("max_salary"),
                        rs.getBigDecimal("min_salary"),
                        rs.getBigDecimal("avg_salary")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q4: " + e.getMessage());
        }
        return new SalaryStatsDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Override
    public List<Department> getDepartmentsByLocation(String location) {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT dept_name, location FROM departments WHERE location = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, location);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Department dept = new Department();
                    dept.setDeptName(rs.getString("dept_name"));
                    dept.setLocation(rs.getString("location"));
                    list.add(dept);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q5: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<String> getEmployeesStartingWith(String prefix) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT name FROM employees WHERE name LIKE ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prefix + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q6: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<EmployeeJobDTO> getUnmanagedEmployees() {
        List<EmployeeJobDTO> list = new ArrayList<>();
        String sql = "SELECT name, job_title FROM employees WHERE manager_id IS NULL";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new EmployeeJobDTO(rs.getString("name"), rs.getString("job_title")));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q7: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<ProjectBudgetDTO> getProjectsAboveBudget(double threshold) {
        List<ProjectBudgetDTO> list = new ArrayList<>();
        String sql = "SELECT p.project_name, d.dept_name, p.budget " +
                     "FROM projects p JOIN departments d ON p.dept_id = d.dept_id " +
                     "WHERE p.budget > ? " +
                     "ORDER BY p.budget DESC";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, threshold);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProjectBudgetDTO(
                            rs.getString("project_name"),
                            rs.getString("dept_name"),
                            rs.getBigDecimal("budget")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q8: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<DeptSalaryStatsDTO> getDeptEmployeeCountAndAvgSalary() {
        List<DeptSalaryStatsDTO> list = new ArrayList<>();
        String sql = "SELECT d.dept_name, COUNT(e.emp_id) AS emp_count, ROUND(AVG(e.salary), 2) AS avg_salary " +
                     "FROM departments d LEFT JOIN employees e ON d.dept_id = e.dept_id " +
                     "GROUP BY d.dept_id, d.dept_name " +
                     "ORDER BY d.dept_id";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new DeptSalaryStatsDTO(
                        rs.getString("dept_name"),
                        rs.getInt("emp_count"),
                        rs.getBigDecimal("avg_salary")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q9: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<EmployeeManagerDTO> getEmployeeManagerHierarchy() {
        List<EmployeeManagerDTO> list = new ArrayList<>();
        String sql = "SELECT e.name AS employee, m.name AS manager " +
                     "FROM employees e LEFT JOIN employees m ON e.manager_id = m.emp_id " +
                     "ORDER BY e.emp_id";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new EmployeeManagerDTO(
                        rs.getString("employee"),
                        rs.getString("manager")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q10: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<ProjectHoursDTO> getTotalHoursPerProject() {
        List<ProjectHoursDTO> list = new ArrayList<>();
        String sql = "SELECT p.project_name, SUM(a.hours) AS total_hours " +
                     "FROM projects p JOIN project_assignments a ON p.project_id = a.project_id " +
                     "GROUP BY p.project_id, p.project_name " +
                     "ORDER BY total_hours DESC";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ProjectHoursDTO(
                        rs.getString("project_name"),
                        rs.getLong("total_hours")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q11: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<String> getEmployeesWithoutProjects() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT e.name " +
                     "FROM employees e LEFT JOIN project_assignments a ON e.emp_id = a.emp_id " +
                     "WHERE a.assign_id IS NULL";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q12: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<DeptAvgSalaryDTO> getDepartmentsWithAvgSalaryAbove(double threshold) {
        List<DeptAvgSalaryDTO> list = new ArrayList<>();
        String sql = "SELECT d.dept_name, AVG(e.salary) AS avg_salary " +
                     "FROM departments d JOIN employees e ON d.dept_id = e.dept_id " +
                     "GROUP BY d.dept_id, d.dept_name " +
                     "HAVING AVG(e.salary) > ? " +
                     "ORDER BY avg_salary DESC";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, threshold);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new DeptAvgSalaryDTO(
                            rs.getString("dept_name"),
                            rs.getBigDecimal("avg_salary")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q13: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<DeptHighestEarnerDTO> getHighestPaidEmployeePerDept() {
        List<DeptHighestEarnerDTO> list = new ArrayList<>();
        String sql = "SELECT d.dept_name, e.name, e.salary " +
                     "FROM employees e JOIN departments d ON e.dept_id = d.dept_id " +
                     "WHERE e.salary = (SELECT MAX(salary) FROM employees WHERE dept_id = e.dept_id) " +
                     "ORDER BY d.dept_name";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new DeptHighestEarnerDTO(
                        rs.getString("dept_name"),
                        rs.getString("name"),
                        rs.getBigDecimal("salary")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q14: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<CrossDeptAssignmentDTO> getCrossDepartmentAssignments() {
        List<CrossDeptAssignmentDTO> list = new ArrayList<>();
        String sql = "SELECT e.name AS employee, ed.dept_name AS home_dept, " +
                     "p.project_name, pd.dept_name AS project_dept " +
                     "FROM project_assignments a " +
                     "JOIN employees e ON a.emp_id = e.emp_id " +
                     "JOIN departments ed ON e.dept_id = ed.dept_id " +
                     "JOIN projects p ON a.project_id = p.project_id " +
                     "JOIN departments pd ON p.dept_id = pd.dept_id " +
                     "WHERE e.dept_id <> p.dept_id " +
                     "ORDER BY e.name";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new CrossDeptAssignmentDTO(
                        rs.getString("employee"),
                        rs.getString("home_dept"),
                        rs.getString("project_name"),
                        rs.getString("project_dept")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error executing Q15: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean resetDatabase() {
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             BufferedReader br = new BufferedReader(new FileReader("sql/setup.sql"))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("--") || line.isEmpty()) continue;
                sb.append(line).append(" ");
                if (line.endsWith(";")) {
                    stmt.execute(sb.toString());
                    sb.setLength(0);
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Failed to reset database: " + e.getMessage());
            return false;
        }
    }
}
