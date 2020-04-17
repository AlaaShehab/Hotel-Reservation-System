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
import java.util.ArrayList;
import java.util.List;
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

    @FXML private Button bookRoom;
    private static Employee employee;
    private List<Room> roomList;
    private Reservation reservation;

    private String checkInDate = "";
    private String checkOutDate = "";

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

        SignIn signInController = loader.getController();
        employee = signInController.getEmployee();
        init();
    }
    private void init () {
        roomNumber.setCellValueFactory(new PropertyValueFactory<Room, String>("roomNumber"));
        floorNumber.setCellValueFactory(new PropertyValueFactory<Reservation, String>("floorNumber"));
        numberOfBeds.setCellValueFactory(new PropertyValueFactory<Reservation, String>("numberOfBeds"));
        type.setCellValueFactory(new PropertyValueFactory<Reservation, String>("type"));
        view.setCellValueFactory(new PropertyValueFactory<Reservation, String>("view"));
        numberOfBathrooms.setCellValueFactory(new PropertyValueFactory<Reservation, String>("numberOfBathrooms"));
        price.setCellValueFactory(new PropertyValueFactory<Reservation, String>("price"));

        ManageDataBase activity = new ManageDataBase();
        roomList = activity.getRoomsInBranch(employee.getHotelID(), employee.getBranchID());
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
        Parent root = FXMLLoader.load(getClass().getResource("../View/staffHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
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
            setReservation(reservation);

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../View/reservationDetails.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        }
    }

    private void createReservation(Room room) {
        reservation.setCheckINDate(checkInDate);
        reservation.setCheckOUTDate(checkOutDate);
        reservation.setResrvationID((int)Math.random());
        reservation.setUserID(0);
//        reservation.setCustomerName("");
//        reservation.setCustomerPhone("");
//        reservation.setRoomPrice(room.getPrice());
        reservation.setRoomNO(room.getRoomNO());
        reservation.setBranchID(room.getBranchID());
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
}
