package org.example.lab6_javafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class PaymentInfoController {
    @FXML
    public VBox root_PaymentInfo;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> fullNameColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, Integer> hoursWorkedColumn;

    @FXML
    private TableColumn<Employee, Double> hourlyRateColumn;

    @FXML
    private TableColumn<Employee, Double> paymentColumn;

    public void initialize() {
        // Determining what data will be displayed in each column
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("hoursWorked"));
        hourlyRateColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("hourlyRate"));
        paymentColumn.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            double payment = employee.getHourlyRate() * employee.getHoursWorked();
            return new SimpleDoubleProperty(payment).asObject();
        });

    }

    void setEmployees(List<Employee> employees) {
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(employees);
        tableView.setItems(employeeObservableList);
    }
    public void fromDisplayForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
            Parent newContent = loader.load();

            MainUIController MainUI = loader.getController();

            root_PaymentInfo.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
