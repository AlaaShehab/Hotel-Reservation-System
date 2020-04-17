package Controller;

import Model.ManageDataBase;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/staffHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public void addRoomHandler(ActionEvent actionEvent) {
        if (type.getText().isEmpty() || view.getText().isEmpty()
                || !InputValidator.validInteger(roomNumber)
                || !InputValidator.validInteger(numberOfBathrooms)
                || !InputValidator.validInteger(numberOfBeds)
                || !InputValidator.validInteger(floorNumber)
                || !InputValidator.validInteger(price)) {
            PopUpMessages.errorMsg("Invalid Room properties!");
        }
        Room room = new Room();
        room.setView(view.getText());
        room.setType(type.getText());
        room.setRoomNumber(Integer.parseInt(roomNumber.getText()));
        room.setFloorNumber(Integer.parseInt(floorNumber.getText()));
        room.setPrice(Integer.parseInt(price.getText()));
        room.setNumberOfBeds(Integer.parseInt(numberOfBeds.getText()));
        room.setNumberOfBathrooms(Integer.parseInt(numberOfBathrooms.getText()));

        ManageDataBase activity = new ManageDataBase();
        if (!activity.addRoom(room)) {
            PopUpMessages.errorMsg("Could not add room!");
        }
        PopUpMessages.successMsg("Room added Successfully!");
    }
}