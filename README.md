# DataForge — Employee Management System (hr_db)
**Course Project**: DBMS × Object-Oriented Programming (Java)  
**Scenario**: Scenario 2 — HR and Project Management  
**Status**: Completed & Verified against Faculty Solution Key  

---

## 1. Quick Start Guide

### Prerequisites
- **Java**: JDK 17+ or JDK 21+ installed and on PATH (`java -version`, `javac -version`)
- **MySQL Server**: Running on `localhost:3306` with credentials configured in `db.properties`
- **MySQL Connector JAR**: Pre-downloaded in `lib/mysql-connector-j-8.3.0.jar`

### How to Compile & Run

#### Option A: Windows Double-Click (Recommended)
Simply double-click **`run.bat`** in the project folder!

#### Option B: PowerShell
```powershell
# 1. Compile
.\compile.ps1

# 2. Run
.\run.ps1
```

#### Option C: Manual Command Line
```cmd
javac -cp ".;lib/mysql-connector-j-8.3.0.jar;bin" -d bin src/com/DataForge/**/*.java src/com/DataForge/*.java
java -cp "bin;lib/mysql-connector-j-8.3.0.jar;." com.DataForge.Main
```

---

## 2. Interactive CLI Menu Overview

When the application runs, you can access individual queries or run the complete suite:

```
===============================================================================
                 DataForge - EMPLOYEE MANAGEMENT SYSTEM (hr_db)               
                 DBMS x OOP Java Course Project (Scenario 2)                   
===============================================================================
  [EASY QUERIES (Q1 - Q8, 5 pts each)]
   1. Q1: List IT department employees (name, job_title)
   2. Q2: Employees earning > 60000 (highest salary first)
   3. Q3: Employees hired after 2021-01-01 (by hire date)
   4. Q4: Total employees, max, min, and average salary
   5. Q5: Departments located in Chennai
   6. Q6: Employees whose name starts with 'S'
   7. Q7: Employees who have no manager (Directors/Heads)
   8. Q8: Projects with budget > 700000 (largest first)
-------------------------------------------------------------------------------
  [MEDIUM QUERIES (Q9 - Q15, 10 pts each)]
   9. Q9: Dept employee count & avg salary (includes empty depts)
  10. Q10: Employee name with manager name (Self Join)
  11. Q11: Total hours logged on each project (highest first)
  12. Q12: Employees not assigned to any project (Anti-Join)
  13. Q13: Departments with average salary > 60000 (HAVING clause)
  14. Q14: Highest-paid employee in each department (Subquery)
  15. Q15: Cross-department project assignments (Multi-table join)
-------------------------------------------------------------------------------
  [SYSTEM UTILITIES]
  16. Run All 15 Reports Sequentially (Batch Faculty Demo)
  17. Reset / Reseed Database from sql/setup.sql
   0. Exit
===============================================================================
```

> **Faculty Presentation Tip**: Choose option **`16`** to run all 15 reports sequentially in one go with clean tabular outputs!

---

## 3. OOP Concepts & Software Architecture

This project was built to satisfy both **DBMS** and **Object-Oriented Programming (OOP)** evaluation rubrics:

1. **Encapsulation**:
   - Entities (`Employee`, `Department`, `Project`, `ProjectAssignment`) hide their internal states behind private attributes and expose getters and setters.
2. **Abstraction**:
   - The `ReportDAO` interface defines high-level contracts for business reports, decoupling the user interface (`Main`) from underlying SQL/JDBC implementation details (`ReportDAOImpl`).
3. **Data Transfer Object (DTO) Pattern**:
   - Multi-table join results are encapsulated into clean DTO objects (`DeptSalaryStatsDTO`, `EmployeeManagerDTO`, `CrossDeptAssignmentDTO`, `SalaryStatsDTO`, `ProjectBudgetDTO`, `ProjectHoursDTO`, `DeptAvgSalaryDTO`, `DeptHighestEarnerDTO`).
4. **Singleton Pattern**:
   - `DatabaseConnection` ensures a single shared connection manager across the entire application lifecycle, preventing connection exhaustion and resource leaks.
5. **Separation of Concerns (Layered Architecture)**:
   - **Presentation Layer**: `Main.java` and `TableRenderer.java`
   - **Service / DAO Layer**: `ReportDAO.java` and `ReportDAOImpl.java`
   - **Domain & DTO Layer**: `com.DataForge.model` and `com.DataForge.dto`
   - **Persistence Layer**: MySQL 8.0 `hr_db`

---

## 4. Database Schema & Tables

- `departments` (`dept_id PK`, `dept_name`, `location`)
- `employees` (`emp_id PK`, `name`, `dept_id FK`, `manager_id FK`, `salary`, `hire_date`, `job_title`)
- `projects` (`project_id PK`, `project_name`, `dept_id FK`, `budget`)
- `project_assignments` (`assign_id PK`, `emp_id FK`, `project_id FK`, `hours`)

---

## 5. All 15 Queries & Official Answer Key Mappings

### Q1: IT Department Employees
```sql
SELECT e.name, e.job_title
FROM employees e JOIN departments d ON e.dept_id = d.dept_id
WHERE d.dept_name = 'IT'
ORDER BY e.emp_id;
```

### Q2: Employees Earning > 60,000
```sql
SELECT name, salary FROM employees WHERE salary > 60000 ORDER BY salary DESC;
```

### Q3: Employees Hired After 2021-01-01
```sql
SELECT name, hire_date FROM employees WHERE hire_date > '2021-01-01' ORDER BY hire_date;
```

### Q4: Total Employees, Max, Min, and Average Salary
```sql
SELECT COUNT(*) AS total_employees, MAX(salary) AS max_salary,
       MIN(salary) AS min_salary, ROUND(AVG(salary), 2) AS avg_salary
FROM employees;
```

### Q5: Departments Located in Chennai
```sql
SELECT dept_name, location FROM departments WHERE location = 'Chennai';
```

### Q6: Employees Whose Name Starts with 'S'
```sql
SELECT name FROM employees WHERE name LIKE 'S%';
```

### Q7: Employees Who Have No Manager
```sql
SELECT name, job_title FROM employees WHERE manager_id IS NULL;
```

### Q8: Projects with Budget > 700,000
```sql
SELECT p.project_name, d.dept_name, p.budget
FROM projects p JOIN departments d ON p.dept_id = d.dept_id
WHERE p.budget > 700000
ORDER BY p.budget DESC;
```

### Q9: Dept Employee Count and Average Salary (Empty Depts Included)
```sql
SELECT d.dept_name, COUNT(e.emp_id) AS emp_count, ROUND(AVG(e.salary), 2) AS avg_salary
FROM departments d LEFT JOIN employees e ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name
ORDER BY d.dept_id;
```

### Q10: Employee and Manager Hierarchy (Self Join)
```sql
SELECT e.name AS employee, m.name AS manager
FROM employees e LEFT JOIN employees m ON e.manager_id = m.emp_id
ORDER BY e.emp_id;
```

### Q11: Total Hours Logged on Each Project
```sql
SELECT p.project_name, SUM(a.hours) AS total_hours
FROM projects p JOIN project_assignments a ON p.project_id = a.project_id
GROUP BY p.project_id, p.project_name
ORDER BY total_hours DESC;
```

### Q12: Employees Not Assigned to Any Project (Anti-Join)
```sql
SELECT e.name
FROM employees e LEFT JOIN project_assignments a ON e.emp_id = a.emp_id
WHERE a.assign_id IS NULL;
```

### Q13: Departments Whose Average Salary is Above 60,000
```sql
SELECT d.dept_name, AVG(e.salary) AS avg_salary
FROM departments d JOIN employees e ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name
HAVING AVG(e.salary) > 60000
ORDER BY avg_salary DESC;
```

### Q14: Highest-Paid Employee in Each Department
```sql
SELECT d.dept_name, e.name, e.salary
FROM employees e JOIN departments d ON e.dept_id = d.dept_id
WHERE e.salary = (SELECT MAX(salary) FROM employees WHERE dept_id = e.dept_id)
ORDER BY d.dept_name;
```

### Q15: Cross-Department Project Assignments
```sql
SELECT e.name AS employee, ed.dept_name AS home_dept, p.project_name, pd.dept_name AS project_dept
FROM project_assignments a
JOIN employees e ON a.emp_id = e.emp_id
JOIN departments ed ON e.dept_id = ed.dept_id
JOIN projects p ON a.project_id = p.project_id
JOIN departments pd ON p.dept_id = pd.dept_id
WHERE e.dept_id <> p.dept_id
ORDER BY e.name;
```
