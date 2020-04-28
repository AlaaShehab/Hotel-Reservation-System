package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StaffHome implements Initializable {

    @FXML private TableView reservations;
    @FXML private TableColumn reservationID;
    @FXML private TableColumn name;
    @FXML private TableColumn mobile;
    @FXML private TableColumn roomNumber;
    @FXML private TableColumn checkedIn;
    @FXML private TableColumn checkedOut;
    @FXML Label hotelName;
    @FXML Label location;
    @FXML Button checkReservation;
    private static Employee employee;
    private static Reservation selectedReservation;
    private static List<Reservation> reservedRooms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignIn signInController = ControllerOperations.getController("../View/signIn.fxml");
        employee = signInController.getEmployee();
        init();
    }
    private void init () {
        reservationID.setCellValueFactory(new PropertyValueFactory<Reservation, String>("reservationID"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<Reservation, String>("roomNO"));
        checkedIn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkINDate"));
        checkedOut.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkOUTDate"));

        //TODO milestone 3
        name.setCellValueFactory(new PropertyValueFactory<Reservation, String>("userName"));
//        mobile.setCellValueFactory(new PropertyValueFactory<Reservation, String>("userPhone"));

        ManageDataBase activity = new ManageDataBase();
        Branch branch = activity.getBranchByEmpID(employee.getEmployeeID());
        hotelName.setText(branch.getHotelName());
        location.setText(branch.getCountry());

        ReservationSearch reservationSearch = new ReservationSearch();
        reservedRooms = reservationSearch.getReservationsOfToday();
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

    public void bookRoomHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/availableRooms.fxml", actionEvent);
    }

    public void addRoomHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/addRoom.fxml", actionEvent);
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/staffHome.fxml", actionEvent);
    }

    public void editProfileHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/employeeProfile.fxml", actionEvent);
    }

    public void filterReservationHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/filterReservations.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Filter Reserved Rooms");
        app_stage.setScene(scene);
        app_stage.show();
        app_stage.setOnCloseRequest(new CloseFilterListener());
    }

    public void setReservedRooms (List<Reservation> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }
    private class CloseFilterListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event){
            refresh();
        }
    }
}
