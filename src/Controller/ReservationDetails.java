package Controller;

import Model.ManageReservation;
import Model.Reservation;
import Model.RoomSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ReservationDetails implements Initializable {

    @FXML TextField checkInDate;
    @FXML TextField checkOutDate;
    @FXML TextField reservationID;
    @FXML TextField customerID;
    @FXML TextField customerName;
    @FXML TextField customerPhone;
    @FXML TextField roomPrice;
    @FXML TextField roomNumber;

    @FXML Button cancelReservation;
    @FXML Button pay;

    private Reservation reservation;
    private boolean alreadyReserved = true;
    private static boolean alreadyPaid = true;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boolean userActivity = false;
        reservation = ((StaffHome)ControllerOperations
                .getController("../View/staffHome.fxml"))
                .getSelectedReservation();
        if (reservation == null) {
            alreadyReserved = false;
            AvailableRooms controller = ControllerOperations
                    .getController("../View/availableRooms.fxml");
            reservation = controller.getReservation();
            userActivity = controller.getUserActivity();
        }

        if (reservation == null) {
            reservation = ((UserReservations)ControllerOperations
                    .getController("../View/userReservations.fxml"))
                    .getSelectedReservation();
            userActivity = true;
        }

        checkInDate.setText(reservation.getCheckINDate());
        checkOutDate.setText(reservation.getCheckOUTDate());
        reservationID.setText(String.valueOf(reservation.getReservationID()));
        customerID.setText(String.valueOf(reservation.getUserID()));
        customerName.setText(reservation.getUserLN());
        customerPhone.setText(reservation.getUserPhoneNumber());
        RoomSearch roomSearch = new RoomSearch();
        roomPrice.setText(String.valueOf(
                roomSearch.
                        getRoomPrice(reservation.getRoomNO(), reservation.getHotelID(), reservation.getBranchID())));
        roomNumber.setText(String.valueOf(reservation.getRoomNO()));

        customerName.setDisable(alreadyReserved || userActivity);
        customerID.setDisable(alreadyReserved || userActivity);
        cancelReservation.setVisible(alreadyReserved);
        pay.setVisible(!reservation.isPaid());

        alreadyPaid = reservation.isPaid();
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage(alreadyReserved ?
                "../View/staffHome.fxml" :
                "../View/availableRooms.fxml",
                actionEvent);
    }

    public void cancelReservationHandler(ActionEvent actionEvent) throws IOException, ParseException {
        ManageReservation manageReservation = new ManageReservation();
        boolean refunded = manageReservation.checkRefund(reservation.getReservationID());
        if (!manageReservation.cancelReservation(reservation.getReservationID())) {
            PopUpMessages.errorMsg("Could not cancel reservation");
            return;
        }

        if (refunded) {
            PopUpMessages.successMsg("Reservation Cancelled! Money will be refunded");
        } else {
            PopUpMessages.successMsg("Reservation Cancelled! No refunded");
        }
        backHandler(actionEvent);
    }

    public void saveReservationHandler(ActionEvent actionEvent) throws IOException, SQLException, ParseException {
        if (!InputValidator.validDate(checkInDate)
                || !InputValidator.validDate(checkOutDate)
                || !InputValidator.validMobileNo(customerPhone)
                || (customerName.getText().isEmpty() && !alreadyReserved)
                || !InputValidator.validInteger(roomNumber)) {
            PopUpMessages.errorMsg("Invalid Reservation!");
            return;
        }
        Reservation newReservation = new Reservation();
        newReservation.setCheckINDate(checkInDate.getText());
        newReservation.setCheckOUTDate(checkOutDate.getText());
        newReservation.setReservationID(Integer.parseInt(reservationID.getText()));
        newReservation.setUserID(Integer.parseInt(customerID.getText()));
        newReservation.setUserLN(customerName.getText());
        newReservation.setUserPhone(customerPhone.getText());
        newReservation.setRoomNO(Integer.parseInt(roomNumber.getText()));
        newReservation.setPaid(alreadyPaid);
        newReservation.setHotelID(reservation.getHotelID());
        newReservation.setBranchID(reservation.getBranchID());

        ManageReservation manageReservation = new ManageReservation();
        if (alreadyReserved) {
            if (!manageReservation.editReservationInfo(newReservation)) {
                PopUpMessages.errorMsg("Could not edit reserved room!");
                return;
            }
        } else {
            if (!manageReservation.addReservation(newReservation)) {
                PopUpMessages.errorMsg("Could not reserve the room!");
                return;
            }
        }

        PopUpMessages.successMsg("Room reserved successfully!");
        backHandler(actionEvent);
    }

    public void payHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/visaPayment.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("VISA PAYMENT");
        app_stage.setScene(scene);
        app_stage.show();
    }

    public void setAlreadyPaid (boolean alreadyPaid) {
        this.alreadyPaid = alreadyPaid;
    }
}
