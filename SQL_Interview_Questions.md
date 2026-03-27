# Top 30 SQL Interview Questions

A curated list of the top 30 SQL interview questions, covering fundamental to advanced concepts commonly asked in technical interviews.

---

## Basic SQL

**1. What is SQL?**
SQL (Structured Query Language) is a standard language for managing and manipulating relational databases. It is used to perform operations such as querying data, inserting records, updating data, and deleting records.

---

**2. What are the different types of SQL commands?**
- **DDL (Data Definition Language):** `CREATE`, `ALTER`, `DROP`, `TRUNCATE`
- **DML (Data Manipulation Language):** `SELECT`, `INSERT`, `UPDATE`, `DELETE`
- **DCL (Data Control Language):** `GRANT`, `REVOKE`
- **TCL (Transaction Control Language):** `COMMIT`, `ROLLBACK`, `SAVEPOINT`

---

**3. What is the difference between `DELETE`, `TRUNCATE`, and `DROP`?**

| Command    | Description |
|------------|-------------|
| `DELETE`   | Removes specific rows based on a `WHERE` clause; can be rolled back. |
| `TRUNCATE` | Removes all rows from a table without logging individual row deletions; faster than `DELETE`; cannot be rolled back in most databases. |
| `DROP`     | Removes the entire table (structure and data) from the database. |

---

**4. What is a Primary Key?**
A primary key is a column (or combination of columns) that uniquely identifies each row in a table. It cannot contain `NULL` values and must be unique.

```sql
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
```

---

**5. What is a Foreign Key?**
A foreign key is a column in one table that references the primary key of another table. It enforces referential integrity between two related tables.

```sql
CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);
```

---

**6. What is the difference between `WHERE` and `HAVING`?**
- `WHERE` filters rows **before** grouping and cannot use aggregate functions.
- `HAVING` filters groups **after** the `GROUP BY` clause and can use aggregate functions.

```sql
-- WHERE: filter individual rows
SELECT * FROM employees WHERE salary > 50000;

-- HAVING: filter groups
SELECT department, AVG(salary) AS avg_salary
FROM employees
GROUP BY department
HAVING AVG(salary) > 60000;
```

---

**7. What is the difference between `INNER JOIN`, `LEFT JOIN`, `RIGHT JOIN`, and `FULL OUTER JOIN`?**

| Join Type        | Returns |
|------------------|---------|
| `INNER JOIN`     | Only rows where there is a match in both tables. |
| `LEFT JOIN`      | All rows from the left table, with matching rows from the right table (NULLs if no match). |
| `RIGHT JOIN`     | All rows from the right table, with matching rows from the left table (NULLs if no match). |
| `FULL OUTER JOIN`| All rows from both tables (NULLs where there is no match). |

```sql
-- INNER JOIN example
SELECT e.name, d.name AS department
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;
```

---

**8. What is a `SELF JOIN`?**
A self join is when a table is joined with itself. It is useful for querying hierarchical data.

```sql
-- Find employees and their managers
SELECT e.name AS employee, m.name AS manager
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.id;
```

---

**9. What is the difference between `UNION` and `UNION ALL`?**
- `UNION` combines results of two queries and removes duplicates.
- `UNION ALL` combines results of two queries and **keeps** duplicates (faster).

```sql
SELECT name FROM employees
UNION
SELECT name FROM contractors;
```

---

**10. What are aggregate functions in SQL?**
Aggregate functions perform calculations on a set of rows and return a single value:
- `COUNT()` – counts rows
- `SUM()` – calculates total
- `AVG()` – calculates average
- `MIN()` – finds minimum value
- `MAX()` – finds maximum value

```sql
SELECT department, COUNT(*) AS total, AVG(salary) AS avg_salary
FROM employees
GROUP BY department;
```

---

## Intermediate SQL

**11. What is a subquery? What are its types?**
A subquery is a query nested inside another query. Types:
- **Scalar subquery:** Returns a single value.
- **Row subquery:** Returns a single row.
- **Table subquery:** Returns a result set (used with `IN`, `EXISTS`, etc.).
- **Correlated subquery:** References a column from the outer query.

```sql
-- Scalar subquery
SELECT name FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);

-- Correlated subquery
SELECT name FROM employees e
WHERE salary > (SELECT AVG(salary) FROM employees WHERE department = e.department);
```

---

**12. What is the difference between `IN` and `EXISTS`?**
- `IN` checks whether a value matches any value in a list or subquery result set.
- `EXISTS` checks whether a subquery returns any rows (more efficient for large datasets).

```sql
-- Using IN
SELECT * FROM employees WHERE department_id IN (SELECT id FROM departments WHERE name = 'IT');

-- Using EXISTS
SELECT * FROM employees e
WHERE EXISTS (SELECT 1 FROM departments d WHERE d.id = e.department_id AND d.name = 'IT');
```

---

**13. What is a View?**
A view is a virtual table based on the result of a SQL query. It does not store data itself but provides a reusable query abstraction.

```sql
CREATE VIEW high_earners AS
SELECT name, department, salary
FROM employees
WHERE salary > 80000;

SELECT * FROM high_earners;
```

---

**14. What is an Index? What are its types?**
An index is a database object that improves the speed of data retrieval. Types:
- **Clustered Index:** Sorts and stores data rows in the table based on the key. One per table.
- **Non-Clustered Index:** Creates a separate structure pointing to data rows. Multiple per table.
- **Unique Index:** Ensures all values in the indexed column are unique.
- **Composite Index:** Index on multiple columns.

```sql
CREATE INDEX idx_employee_name ON employees(name);
CREATE UNIQUE INDEX idx_employee_email ON employees(email);
```

---

**15. What is normalization? What are the normal forms?**
Normalization is the process of organizing a database to reduce redundancy and improve data integrity.

| Normal Form | Description |
|-------------|-------------|
| **1NF**     | Each column contains atomic (indivisible) values; no repeating groups. |
| **2NF**     | Meets 1NF; all non-key attributes are fully dependent on the primary key. |
| **3NF**     | Meets 2NF; no transitive dependencies (non-key columns depend only on the primary key). |
| **BCNF**    | Stronger version of 3NF; every determinant is a candidate key. |

---

**16. What is denormalization?**
Denormalization is the process of intentionally introducing redundancy into a database to improve read performance, at the cost of increased storage and potential data anomalies.

---

**17. What are SQL constraints?**
Constraints enforce rules on data in tables:
- `NOT NULL` – Column cannot have a NULL value.
- `UNIQUE` – All values in a column must be unique.
- `PRIMARY KEY` – Uniquely identifies each row; implies NOT NULL and UNIQUE.
- `FOREIGN KEY` – Enforces referential integrity between tables.
- `CHECK` – Ensures values in a column satisfy a given condition.
- `DEFAULT` – Sets a default value when none is provided.

---

**18. What is a stored procedure?**
A stored procedure is a precompiled set of SQL statements that can be executed with a single call. It supports parameters, conditional logic, and loops.

```sql
DELIMITER //
CREATE PROCEDURE GetEmployeesByDepartment(IN dept_name VARCHAR(100))
BEGIN
    SELECT * FROM employees e
    INNER JOIN departments d ON e.department_id = d.id
    WHERE d.name = dept_name;
END //
DELIMITER ;

CALL GetEmployeesByDepartment('Engineering');
```

---

**19. What is a trigger?**
A trigger is a set of SQL statements that automatically executes (fires) in response to specific events (`INSERT`, `UPDATE`, `DELETE`) on a table.

```sql
CREATE TRIGGER before_employee_insert
BEFORE INSERT ON employees
FOR EACH ROW
SET NEW.created_at = NOW();
```

---

**20. What is a transaction? What are ACID properties?**
A transaction is a sequence of operations performed as a single logical unit of work.

**ACID Properties:**
- **Atomicity:** All operations in a transaction succeed or all are rolled back.
- **Consistency:** A transaction brings the database from one valid state to another.
- **Isolation:** Transactions are isolated from each other until committed.
- **Durability:** Once committed, changes persist even in the event of a system failure.

```sql
BEGIN;
UPDATE accounts SET balance = balance - 500 WHERE id = 1;
UPDATE accounts SET balance = balance + 500 WHERE id = 2;
COMMIT;
```

---

## Advanced SQL

**21. What are window functions in SQL?**
Window functions perform calculations across a set of rows related to the current row, without collapsing them into a single output row (unlike aggregate functions).

```sql
-- ROW_NUMBER: assign a unique row number within each department ordered by salary
SELECT name, department, salary,
       ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) AS rank_in_dept
FROM employees;
```

Common window functions: `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`, `LEAD()`, `LAG()`, `SUM() OVER()`, `AVG() OVER()`.

---

**22. What is the difference between `RANK()`, `DENSE_RANK()`, and `ROW_NUMBER()`?**

| Function       | Behavior on Ties |
|----------------|-----------------|
| `ROW_NUMBER()` | Always assigns a unique number; no ties. |
| `RANK()`       | Tied rows get the same rank; next rank skips numbers (1, 1, 3). |
| `DENSE_RANK()` | Tied rows get the same rank; no numbers are skipped (1, 1, 2). |

---

**23. What is a CTE (Common Table Expression)?**
A CTE is a temporary named result set defined with the `WITH` keyword, used to simplify complex queries and improve readability.

```sql
WITH DepartmentAvg AS (
    SELECT department, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department
)
SELECT e.name, e.salary, d.avg_salary
FROM employees e
JOIN DepartmentAvg d ON e.department = d.department
WHERE e.salary > d.avg_salary;
```

---

**24. What is a recursive CTE?**
A recursive CTE is a CTE that references itself, useful for querying hierarchical or tree-structured data.

```sql
WITH RECURSIVE EmployeeHierarchy AS (
    -- Anchor member
    SELECT id, name, manager_id, 1 AS level
    FROM employees
    WHERE manager_id IS NULL
    UNION ALL
    -- Recursive member
    SELECT e.id, e.name, e.manager_id, h.level + 1
    FROM employees e
    INNER JOIN EmployeeHierarchy h ON e.manager_id = h.id
)
SELECT * FROM EmployeeHierarchy;
```

---

**25. What is the difference between optimistic and pessimistic locking?**
- **Optimistic Locking:** Assumes conflicts are rare. Reads data without locking; checks for conflicts at update time (commonly using a version column). Used in Spring Data JPA with `@Version`.
- **Pessimistic Locking:** Assumes conflicts are likely. Locks data when it is read so no other transaction can modify it until the lock is released.

---

**26. How do you find duplicate records in a table?**

```sql
-- Find names that appear more than once
SELECT name, COUNT(*) AS occurrences
FROM employees
GROUP BY name
HAVING COUNT(*) > 1;

-- Delete duplicates, keeping the one with the lowest id
DELETE FROM employees
WHERE id NOT IN (
    SELECT MIN(id) FROM employees GROUP BY name, email
);
```

---

**27. How do you find the Nth highest salary?**

```sql
-- Using LIMIT/OFFSET (MySQL) — replace 2 with (N - 1) for the Nth highest
-- Example: 3rd highest salary → LIMIT 1 OFFSET 2
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 2;

-- Using DENSE_RANK (standard SQL) — replace 3 with the desired rank N
SELECT salary FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
    FROM employees
) ranked
WHERE rnk = 3;
```

---

**28. What is query optimization? Name some techniques.**
Query optimization is the process of improving SQL query performance:
- **Use indexes** on columns used in `WHERE`, `JOIN`, and `ORDER BY` clauses.
- **Avoid `SELECT *`** – select only needed columns.
- **Use `EXPLAIN`/`EXPLAIN ANALYZE`** to examine the query execution plan.
- **Avoid functions on indexed columns** in `WHERE` clauses.
- **Use `EXISTS` instead of `IN`** for large subqueries.
- **Limit result sets** with pagination (`LIMIT`/`OFFSET`).
- **Avoid correlated subqueries** when a `JOIN` is more efficient.

```sql
-- Use EXPLAIN to analyze query performance
EXPLAIN SELECT * FROM employees WHERE department = 'Engineering';
```

---

**29. What is the difference between `CHAR` and `VARCHAR`?**

| Type      | Storage | Use Case |
|-----------|---------|----------|
| `CHAR(n)` | Fixed-length; always uses `n` bytes, padded with spaces. | Fixed-size data (e.g., country codes, status flags). |
| `VARCHAR(n)` | Variable-length; uses only as many bytes as needed + length overhead. | Variable-size data (e.g., names, email addresses). |

---

**30. What are some common SQL performance anti-patterns to avoid?**
- **`SELECT *`** – Fetches unnecessary columns, increasing I/O and network overhead.
- **N+1 query problem** – Issuing one query per row instead of a single `JOIN` query (common in ORM usage like JPA).
- **Missing indexes** – Full table scans on large tables cause slow queries.
- **Using functions on indexed columns** in `WHERE` (e.g., `WHERE YEAR(created_at) = 2024` prevents index usage).
- **Implicit type conversions** in `JOIN` or `WHERE` conditions that prevent index usage.
- **Unbounded queries** – No `LIMIT` on large tables that can return millions of rows.
- **Overusing subqueries** – Often replaceable with more efficient `JOIN`s or CTEs.

---

*These questions cover a broad range of SQL topics from basic syntax and constraints to advanced features like window functions, CTEs, and query optimization — all relevant to working with Spring Data JPA and MySQL in a Spring Boot application.*
