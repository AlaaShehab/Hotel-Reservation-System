package Controller;

import Model.ManageDataBase;
import Model.Room;
import Model.RoomSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FilterRooms implements Initializable {

    @FXML private Button back;
    @FXML private TextField checkInDate;
    @FXML private TextField checkOutDate;
    @FXML private TextField numberOfBeds;
    @FXML private TextField numberOfBathrooms;
    @FXML private TextField type;
    @FXML private TextField view;
    @FXML private TextField minPrice;
    @FXML private TextField maxPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    public void filterHandler(ActionEvent actionEvent) throws IOException {
        if (!InputValidator.validDate(checkInDate)
                || !InputValidator.validDate(checkOutDate)
                || !InputValidator.validPriceRange(minPrice, maxPrice)
                || !InputValidator.validDateRange(checkInDate, checkOutDate)
                || !InputValidator.validFilterNumber(numberOfBathrooms)
                || !InputValidator.validFilterNumber(numberOfBeds)) {
            PopUpMessages.errorMsg("Invalid Filter");
        }
        RoomSearch roomSearch = new RoomSearch();
        roomSearch.setCheckInDate(checkInDate.getText());
        roomSearch.setCheckOutDate(checkOutDate.getText());
        roomSearch.setMinPrice(minPrice.getText().isEmpty()
                ? -1
                : Integer.parseInt(minPrice.getText()));
        roomSearch.setMaxPrice(maxPrice.getText().isEmpty()
                ? Integer.MIN_VALUE
                : Integer.parseInt(maxPrice.getText()));
        roomSearch.setType(type.getText());
        roomSearch.setView(view.getText());
        roomSearch.setNumberOfBeds(
                numberOfBeds.getText().isEmpty()
                        ? Integer.MAX_VALUE
                        : Integer.parseInt(numberOfBeds.getText()));
        roomSearch.setNumberOfBathrooms(
                numberOfBathrooms.getText().isEmpty()
                        ? Integer.MAX_VALUE
                        : Integer.parseInt(numberOfBathrooms.getText()));

        ManageDataBase activity = new ManageDataBase();
        List<Room> searchedRooms = activity.getRooms(roomSearch);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/availableRooms.fxml"));
        loader.load();
        AvailableRooms controller = loader.getController();
        controller.setRooms(searchedRooms);
        controller.setCheckInDate(checkInDate.getText());
        controller.setCheckOutDate(checkOutDate.getText());

        Stage stage = (Stage) back.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }
}
