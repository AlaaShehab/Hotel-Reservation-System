package Controller;

import Model.ManageDataBase;
import Model.Reservation;
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
    private boolean alreadyPaid = true;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservation = getStaffHomeController().getSelectedReservation();
        if (reservation == null) {
            alreadyReserved = false;
            reservation = getAvailableRoomsController().getReservation();
        }

        checkInDate.setText(reservation.getCheckInDate());
        checkOutDate.setText(reservation.getCheckOutDate());
        reservationID.setText(reservation.getReservationID());
        customerID.setText(reservation.getCustomerID());
        customerName.setText(reservation.getCustomerName());
        customerPhone.setText(reservation.getCustomerPhone());
        roomPrice.setText(reservation.getRoomPrice());
        roomNumber.setText(reservation.getRoomNumber());

        customerName.setDisable(alreadyReserved);
        customerID.setDisable(alreadyReserved);
        cancelReservation.setVisible(alreadyReserved);
        pay.setVisible(!reservation.isPaid());

        alreadyPaid = reservation.isPaid();
    }
    private StaffHome getStaffHomeController () {
        FXMLLoader loader = getLoader("../View/staffHome.fxml");
        return loader.getController();
    }

    private AvailableRooms getAvailableRoomsController () {
        FXMLLoader loader = getLoader("../View/availableRooms.fxml");
        return loader.getController();
    }

    private FXMLLoader getLoader (String controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(controller));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }
        return loader;
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        getStaffHomeController().setSelectedReservation(null);
        Parent root = FXMLLoader.load(getClass().getResource(
                alreadyReserved ?
                        "../View/staffHome.fxml" :
                        "../View/availableRooms.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public void cancelReservationHandler(ActionEvent actionEvent) throws IOException {
        ManageDataBase activity = new ManageDataBase();
        boolean refunded = activity.cancellationISRefunded(reservation.getID());
        if (!activity.cancelReservation(reservation.getID())) {
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

    public void saveReservationHandler(ActionEvent actionEvent) throws IOException {
        if (!InputValidator.validDate(checkInDate)
                || !InputValidator.validDate(checkOutDate)
                || !InputValidator.validMobileNo(customerPhone)
                || customerName.getText().isEmpty()) {
            PopUpMessages.errorMsg("Invalid Reservation!");
            return;
        }
        Reservation newReservation = new Reservation();
        newReservation.setCheckInDate(checkInDate.getText());
        newReservation.setCheckOutDate(checkOutDate.getText());
        newReservation.setReservationID(Integer.parseInt(reservationID.getText()));
        newReservation.setCustomerID(customerID.getText());
        newReservation.setCustomerName(customerName.getText());
        newReservation.setCustomerPhone(customerPhone.getText());
        newReservation.setRoomPrice(Integer.parseInt(roomPrice.getText()));
        newReservation.setRoomNumber(roomNumber.getText());
        newReservation.isPaid(alreadyPaid);

        ManageDataBase activity = new ManageDataBase();
        if (activity.addReservation(reservation)) {
            PopUpMessages.errorMsg("Could not reserve the room!");
            return;
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
