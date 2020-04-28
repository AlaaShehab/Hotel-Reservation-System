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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class AvailableRooms implements Initializable {

    @FXML private TableView rooms;
    @FXML private TableColumn roomNumber;
    @FXML private TableColumn floorNumber;
    @FXML private TableColumn numberOfBeds;
    @FXML private TableColumn type;
    @FXML private TableColumn view;
    @FXML private TableColumn numberOfBathrooms;
    @FXML private TableColumn price;
    @FXML private TableColumn hotelName;
    @FXML private TableColumn hotelCity;

    @FXML private Button bookRoom;
    private static Employee employee;
    private static User user;
    private static List<Room> roomList;
    private static Reservation reservation;

    private static boolean userActivity = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignIn signInController = ControllerOperations.getController("../View/signIn.fxml");
        employee = signInController.getEmployee();
        if (employee == null) {
            user = signInController.getUser();
            userActivity = true;
        }
        try {
            init();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void init () throws ParseException {
        roomNumber.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomNO"));
        floorNumber.setCellValueFactory(new PropertyValueFactory<Room, Integer>("floorNO"));
        numberOfBeds.setCellValueFactory(new PropertyValueFactory<Room, Integer>("bedsNO"));
        type.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
        view.setCellValueFactory(new PropertyValueFactory<Room, String>("roomView"));
        numberOfBathrooms.setCellValueFactory(new PropertyValueFactory<Room, Integer>("bathRoomNO"));
        price.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));
        hotelName.setCellValueFactory(new PropertyValueFactory<Room, String>("hotelName"));
        hotelCity.setCellValueFactory(new PropertyValueFactory<Room, String>("hotelCity"));

        if (userActivity) {
            roomList = ((UserHome) ControllerOperations.getController("../View/userHome.fxml")).getSearchedRooms();
        } else {
            RoomSearch roomSearch = new RoomSearch();
            roomList = roomSearch.getAvailableRooms(employee.getHotelID(), employee.getBranchID(), "", 1);
        }
        refresh();
        bookRoom.setOnAction(new BookRoomListener());
    }

    public void refresh () {
        rooms.setItems(FXCollections.observableList(new ArrayList<>()));
        rooms.getItems().addAll(getTableData());
    }
    private ObservableList getTableData() {
        ObservableList data = FXCollections.observableList(roomList);
        return data;
    }

    public void filterRoomsHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/filterRooms.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = new Stage();
        app_stage.setTitle("Filter Rooms");
        app_stage.setScene(scene);
        app_stage.show();
        app_stage.setOnCloseRequest(new CloseFilterListener());
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage(userActivity ? "../View/userHome.fxml" : "../View/staffHome.fxml", actionEvent);
    }

    private class CloseFilterListener implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event){
            refresh();
        }
    }

    private class BookRoomListener implements EventHandler {
        @Override
        public void handle(Event event){
            //TODO handle if no item is selected (i.e. ix is out of range)
            int ix = rooms.getSelectionModel().getSelectedIndex();
            if (ix < 0) {
                PopUpMessages.errorMsg("Please select a valid room");
                return;
            }
            Room room = (Room) rooms.getSelectionModel().getSelectedItem();
            createReservation(room);

            try {
                ControllerOperations.loadPage("../View/reservationDetails.fxml", event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createReservation(Room room) {
        Random random = new Random();
        reservation = new Reservation();
        reservation.setCheckINDate(room.getCheckINdate());
        reservation.setCheckOUTDate(room.getCheckOUTdate());
        reservation.setReservationID(random.nextInt(100));
        reservation.setUserID(user == null ? 1 : user.getUserID());
        reservation.setUserLN(user == null ? "" : user.getLastName());
        reservation.setUserPhoneNumber(user == null ? "" : user.getPhoneNumber());
        reservation.setRoomNO(room.getRoomNO());
        reservation.setBranchID(room.getBranchID());
        reservation.setHotelID(room.getHotelID());
        reservation.setPaid(false);
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public Reservation getReservation () {
        return reservation;
    }

    public int getBranchID () {
        return employee.getBranchID();
    }

    public void setRooms (List<Room> roomList) {
        this.roomList = roomList;
    }

    public boolean getUserActivity () {
        return userActivity;
    }
}
