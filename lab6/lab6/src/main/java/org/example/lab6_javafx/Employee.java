package org.example.lab6_javafx;

import java.io.Serializable;

public class Employee implements Serializable {
    String department;
    String fullName;
    String position;
    String qualification;
    int hoursWorked;
    double hourlyRate;
    int id;
    public Employee(int id, String department, String fullName, String position, String qualification, int hoursWorked, double hourlyRate) {
        this.id = id;
        this.department = department;
        this.fullName = fullName;
        this.position = position;
        this.qualification = qualification;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public Employee() {
    }
    public int getId() {
        return id;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department=department;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
