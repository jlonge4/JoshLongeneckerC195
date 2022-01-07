package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import model.Data;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AddAppointment implements Initializable {
    public TextField appointmentId;
    public TextField userId;
    public TextField title;
    public TextField description;
    public TextField location;
    public TextField type;
    public ComboBox contact;
    public TextField customerId;
    public DatePicker start;
    public DatePicker end;
    public ComboBox hoursOne;
    public ComboBox minutesTwo;
    public ComboBox hoursTwo;
    public ComboBox minutesOne;
    private ObservableList<String> contacts = FXCollections.observableArrayList();
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        hoursOne.setItems(hours);
        hoursTwo.setItems(hours);
        minutesOne.setItems(minutes);
        minutesTwo.setItems(minutes);
        start.setOnAction(event -> {
            LocalDate date = start.getValue();
            System.out.println("Selected date: " + date);
        });
        end.setOnAction(event -> {
            LocalDate date = end.getValue();
            System.out.println("Selected date: " + date);
        });

        Date date=java.util.Calendar.getInstance().getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String date1 = format1.format(date);


        System.out.println("Add Customer" + date1);
        userId.setText(String.valueOf(Login.returnUser()));
        int id;
        Customer customer;
        customer = MainMenu.getSelectedAptCustomer();
        customerId.setText(String.valueOf(customer.getId()));
        try {
            contacts = (Data.getContacts());
            contact.setItems(contacts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int increaseCount(int count) {
        count ++;
        return count;
    }

    public int randomId() throws SQLException {
        AtomicInteger randomId = new AtomicInteger(increaseCount(Data.getAllAppointments().size()));
        Data.getAllAppointments().forEach((item) -> {
            if (item.getId() == randomId.get()) {
                randomId.addAndGet(1);
            };
        });
        return randomId.get();
    }

    public void MainMenu (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 500);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onAppointmentSave(ActionEvent event) throws SQLException, IOException, NullPointerException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        int appointmentId = randomId();
        String titleApt = "";
        String descriptionApt = "";
        String locationApt = "";
        String contactApt = "";
        String typeApt = "";
        String startApt = "2020-05-29 12:00:00";
        String endApt = "2020-05-29 13:00:00";
        int custIdApt = 0;
        int userIdApt = Login.returnUser();

        try {
            titleApt = title.getText();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            descriptionApt = description.getText();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            locationApt = location.getText();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            contactApt = (String) contact.getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            typeApt = type.getText();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            descriptionApt = description.getText();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            LocalDateTime date = start.getValue().atTime(hoursOne.getSelectionModel().getSelectedIndex(),minutesOne.getSelectionModel().getSelectedIndex());
            startApt = date.format(formatter);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            LocalDateTime date = end.getValue().atTime(hoursTwo.getSelectionModel().getSelectedIndex(),minutesTwo.getSelectionModel().getSelectedIndex());
            endApt = date.format(formatter);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            custIdApt = Integer.parseInt(customerId.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            userIdApt = Integer.parseInt(userId.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the customer name text field");
            alert.showAndWait();
        }
        try {
            System.out.println(startApt);
        Appointment appointment = new Appointment(appointmentId, titleApt, descriptionApt, locationApt, contactApt, typeApt, startApt, endApt, custIdApt, userIdApt);
        Data.addAppointment(appointment);
        MainMenu(event);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Failed");
            alert.setContentText("please fill in or correct the appointment text fields");
            alert.showAndWait();
        }

    }























}
