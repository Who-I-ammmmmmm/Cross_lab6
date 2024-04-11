package org.example.lab6_javafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    public Connection connection;

    public EmployeeRepository() {
        try {
            // connection to database
            connection = DriverManager.getConnection("jdbc:sqlite:employees.db");
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create table,if it does not exist
   public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "department TEXT," +
                "full_name TEXT," +
                "position TEXT," +
                "qualification TEXT," +
                "hours_worked INTEGER," +
                "hourly_rate REAL)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(int employeeId) {
        // prepare SQL request for deleting employee
        String sql = "DELETE FROM employees WHERE id = ?";

        try {
            // create PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            // Install parameter id of employee
            statement.setInt(1, employeeId);
            //  SQL request
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // add employee into database
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (department, full_name, position, qualification, hours_worked, hourly_rate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.department);
            preparedStatement.setString(2, employee.fullName);
            preparedStatement.setString(3, employee.position);
            preparedStatement.setString(4, employee.qualification);
            preparedStatement.setInt(5, employee.hoursWorked);
            preparedStatement.setDouble(6, employee.hourlyRate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // output all employees from database
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("department"),
                        resultSet.getString("full_name"),
                        resultSet.getString("position"),
                        resultSet.getString("qualification"),
                        resultSet.getInt("hours_worked"),
                        resultSet.getDouble("hourly_rate")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // close connection with database
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
