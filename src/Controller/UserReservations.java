package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserReservations implements Initializable {

    @FXML private TableView reservations;
    @FXML private Button checkReservation;

    @FXML private TableColumn roomPrice;
    @FXML private TableColumn hotelLocation;
    @FXML private TableColumn hotelName;
    @FXML private TableColumn roomNumber;
    @FXML private TableColumn checkedIn;
    @FXML private TableColumn checkedOut;

    private User user;
    private List<Reservation> reservedRooms;
    private static Reservation selectedReservation;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignIn signInController = ControllerOperations.getController("../View/signIn.fxml");
        user = signInController.getUser();
    }
    private void init () {
        roomNumber.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("roomNO"));
        checkedIn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkINDate"));
        checkedOut.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkOUTDate"));
        hotelName.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hotelName"));
        hotelLocation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("hotelCity"));
        hotelLocation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("roomPrice"));

        ReservationSearch reservationSearch = new ReservationSearch();
        reservedRooms = reservationSearch.getUserReservations(user.getUserID());
        refresh();
        checkReservation.setOnAction(new CheckReservationListener());
    }

    public void refresh () {
        reservations.setItems(FXCollections.observableList(new ArrayList<>()));
        reservations.getItems().addAll(getTableData());
    }
    private ObservableList getTableData() {
        ObservableList data = FXCollections.observableList(reservedRooms);
        return data;
    }

    private class CheckReservationListener implements EventHandler {
        @Override
        public void handle(Event event){
            int ix = reservations.getSelectionModel().getSelectedIndex();
            if (ix < 0) {
                PopUpMessages.errorMsg("Please select a valid reservation");
                return;
            }
            Reservation reservation = (Reservation) reservations.getSelectionModel().getSelectedItem();
            setSelectedReservation(reservation);

            try {
                ControllerOperations.loadPage("../View/reservationDetails.fxml", event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Reservation getSelectedReservation () {
        return selectedReservation;
    }

    public void setSelectedReservation (Reservation selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/userHome.fxml", actionEvent);
    }
}
