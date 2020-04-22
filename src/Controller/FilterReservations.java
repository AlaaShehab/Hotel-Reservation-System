package Controller;

import Model.ManageDataBase;
import Model.Reservation;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void filterHandler(ActionEvent actionEvent) throws IOException {
        if (!InputValidator.validDate(checkIn)) {
            PopUpMessages.errorMsg("Invalid date");
        }
        Reservation reservation = new Reservation();
        reservation.setCheckINDate(checkIn.getText());
//        reservation.setCustomerName(customerName);
//        reservation.setCustomerPhone(customerPhone);
        reservation.setUserID(Integer.parseInt(customerID.getText()));
        reservation.setRoomNO(Integer.parseInt(roomNumber.getText()));
        reservation.setPaid(paid.isSelected());

        ManageDataBase activity = new ManageDataBase();
        //List<Reservation> reservedRooms = activity.getReservations(reservation);
        List<Reservation> reservedRooms = new ArrayList<>();
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
