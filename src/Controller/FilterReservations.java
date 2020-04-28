package Controller;

import Model.Employee;
import Model.ManageDataBase;
import Model.Reservation;
import Model.ReservationSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FilterReservations implements Initializable {
    @FXML Button cancel;
    @FXML TextField checkIn;
    @FXML TextField customerName;
    @FXML TextField customerPhone;
    @FXML TextField customerID;
    @FXML TextField roomNumber;
    @FXML CheckBox paid;

    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/signIn.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        SignIn signInController = ControllerOperations.getController("../View/signIn.fxml");
        employee = signInController.getEmployee();
    }

    public void filterHandler(ActionEvent actionEvent) throws IOException {
        if (!InputValidator.validDate(checkIn) && !checkIn.getText().isEmpty()) {
            PopUpMessages.errorMsg("Invalid date");
        }
        Reservation reservation = new Reservation();
        reservation.setCheckINDate(checkIn.getText());
        reservation.setUserLN(customerName.getText());
        reservation.setPhoneNO(customerPhone.getText());
        reservation.setUserID(
                customerID.getText().isEmpty()
                        ? -1
                        : Integer.parseInt(customerID.getText()));
        reservation.setRoomNO(
                roomNumber.getText().isEmpty()
                        ? -1
                        : Integer.parseInt(roomNumber.getText()));
        reservation.setPaid(paid.isSelected());
        reservation.setHotelID(employee.getHotelID());
        reservation.setBranchID(employee.getBranchID());

        ReservationSearch search = new ReservationSearch();
        List<Reservation> reservedRooms = search.filterReservations(reservation);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/staffHome.fxml"));
        Parent root = (Parent) loader.load();
        StaffHome controller = loader.getController();
        controller.setReservedRooms(reservedRooms);

        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }

    public void CancelHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
