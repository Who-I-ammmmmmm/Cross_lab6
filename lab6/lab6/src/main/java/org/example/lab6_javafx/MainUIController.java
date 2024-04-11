package org.example.lab6_javafx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class MainUIController {
    public EmployeeRepository employeeRepository = new EmployeeRepository();
    @FXML
    public VBox root_MainUI;
    @FXML
    public TextField departmentField;
    @FXML
    public TextField fullNameField;
    @FXML
    public TextField positionField;
    @FXML
    public TextField qualificationField;
    @FXML
    public TextField hoursWorkedField;
    @FXML
    public TextField hourlyRateField;
    @FXML
    public void addEmployee() {
        int id = 0;
        String department = departmentField.getText();
        String fullName = fullNameField.getText();
        String position = positionField.getText();
        String qualification = qualificationField.getText();
        int hoursWorked = Integer.parseInt(hoursWorkedField.getText());
        double hourlyRate = Double.parseDouble(hourlyRateField.getText());

        Employee employee = new Employee(id,department, fullName, position, qualification, hoursWorked, hourlyRate);
        employeeRepository.addEmployee(employee);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Додано працівника");
        alert.setHeaderText(null);
        alert.setContentText("Працівник успішно доданий!");
        alert.showAndWait();
    }

    @FXML
    public void toEmployeeForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addEmployee.fxml"));
            Parent newContent = loader.load();

            MainUIController MainUI = loader.getController();

            root_MainUI.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void toPaymentForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentInfo.fxml"));
            Parent newContent = loader.load();

            PaymentInfoController paymentInfo = loader.getController();
            List<Employee> employees = employeeRepository.getAllEmployees();
            paymentInfo.setEmployees(employees);

            root_MainUI.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toDisplayData() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayData.fxml"));
            Parent newContent = loader.load();

            DisplayData SearchByPosition = loader.getController();
            List<Employee> displayData = employeeRepository.getAllEmployees();
            SearchByPosition.setEmployees(displayData);

            root_MainUI.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Вихід з програми");
        alert.setHeaderText(null);
        alert.setContentText("До побачення!");
        alert.showAndWait();
        System.exit(0);
    }
}
