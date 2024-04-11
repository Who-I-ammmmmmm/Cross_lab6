package org.example.lab6_javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class DisplayData {
    public EmployeeRepository employeeRepository = new EmployeeRepository();
    @FXML
    private TextField searchField;
    @FXML
    public VBox root_DisplayData;
    @FXML
    private ComboBox<String> searchCriteriaComboBox;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> departmentColumn;
    @FXML
    private TableColumn<Employee, String> fullNameColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, String> qualificationColumn;
    @FXML
    private TableColumn<Employee, Integer> hoursWorkedColumn;
    @FXML
    private TableColumn<Employee, Double> hourlyRateColumn;

    private List<Employee> allEmployees; // Assuming you have a list of all employees

    public void setEmployees(List<Employee> employees) {
        allEmployees = employees;
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(employees);
        tableView.setItems(employeeObservableList);
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        qualificationColumn.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
        hourlyRateColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));

        // Add listener to searchField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchEmployees());
    }

    @FXML
    public void searchEmployees() {
        String searchTerm = searchField.getText().toLowerCase();
        String searchCriteria = searchCriteriaComboBox.getValue(); // output selected search parameter

        ObservableList<Employee> filteredList = FXCollections.observableArrayList();

        for (Employee employee : allEmployees) {
            String fieldValue = "";
            if ("Position".equals(searchCriteria)) {
                fieldValue = employee.getPosition().toLowerCase();
            } else if ("Qualification".equals(searchCriteria)) {
                fieldValue = employee.getQualification().toLowerCase();
            }else if ("Department".equals(searchCriteria)) {
                fieldValue = employee.getDepartment().toLowerCase();
            }
            if (fieldValue.contains(searchTerm)) {
                filteredList.add(employee);
            }
        }

        tableView.setItems(filteredList);
    }

    public void fromDisplayForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
            Parent newContent = loader.load();

            MainUIController MainUI = loader.getController();

            root_DisplayData.getChildren().setAll(newContent);
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

            root_DisplayData.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void deleteEmployee() {
        Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            // remove selected employee from database
            employeeRepository.deleteEmployee(selectedEmployee.getId());
            System.out.print(selectedEmployee.getId());
            // update list of employee
            List<Employee> updatedEmployees = employeeRepository.getAllEmployees();
            setEmployees(updatedEmployees);
        }
    }



}

