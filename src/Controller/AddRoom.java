package Controller;

import Model.Employee;
import Model.ManageDataBase;
import Model.ManageRoom;
import Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddRoom implements Initializable {

    @FXML private TextField roomNumber;
    @FXML private TextField floorNumber;
    @FXML private TextField numberOfBeds;
    @FXML private TextField numberOfBathrooms;
    @FXML private TextField type;
    @FXML private TextField view;
    @FXML private TextField price;

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

        SignIn signInController = loader.getController();
        employee = signInController.getEmployee();
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/staffHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public void addRoomHandler(ActionEvent actionEvent) throws SQLException {
        if (type.getText().isEmpty() || view.getText().isEmpty()
                || !InputValidator.validInteger(roomNumber)
                || !InputValidator.validInteger(numberOfBathrooms)
                || !InputValidator.validInteger(numberOfBeds)
                || !InputValidator.validInteger(floorNumber)
                || !InputValidator.validInteger(price)) {
            PopUpMessages.errorMsg("Invalid Room properties!");
        }
        Room room = new Room();
        room.setRoomView(view.getText());
        room.setRoomType(type.getText());
        room.setRoomNO(Integer.parseInt(roomNumber.getText()));
        room.setFloorNO(Integer.parseInt(floorNumber.getText()));
        room.setPrice(Integer.parseInt(price.getText()));
        room.setBedsNO(Integer.parseInt(numberOfBeds.getText()));
        room.setBathRoomNO(Integer.parseInt(numberOfBathrooms.getText()));
        room.setHotelID(employee.getHotelID());
        room.setBranchID(employee.getBranchID());
        room.setReserved(false);

        ManageRoom manageRoom = new ManageRoom();
        if (!manageRoom.addRoom(room)) {
            PopUpMessages.errorMsg("Could not add room!");
        }
        PopUpMessages.successMsg("Room added Successfully!");
    }
}
