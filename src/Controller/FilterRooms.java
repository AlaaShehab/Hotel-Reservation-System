package Controller;

import Model.Employee;
import Model.Room;
import Model.RoomSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
    @FXML private TextField hotelName;
    @FXML private TextField hotelCity;

    private Employee employee;
    private boolean userActivity;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignIn signInController = ControllerOperations.getController("../View/signIn.fxml");
        employee = signInController.getEmployee();
        if (employee == null) {
            userActivity = true;
            hotelCity.setDisable(false);
            hotelName.setDisable(false);
        }
    }

    public void backHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    public void filterHandler(ActionEvent actionEvent) throws IOException, ParseException {
        if ((!InputValidator.validDate(checkInDate) && !checkInDate.getText().isEmpty())
                || (!InputValidator.validDate(checkOutDate) && !checkOutDate.getText().isEmpty())
                || !InputValidator.validPriceRange(minPrice, maxPrice)
                || !InputValidator.validDateRange(checkInDate, checkOutDate)
                || !InputValidator.validFilterNumber(numberOfBathrooms)
                || !InputValidator.validFilterNumber(numberOfBeds)
                || !InputValidator.validName(hotelName)
                || !InputValidator.validName(hotelCity)) {
            PopUpMessages.errorMsg("Invalid Filter");
        }
        Room room = new Room();
        room.setCheckINdate(checkInDate.getText());
        room.setCheckOUTdate(checkOutDate.getText());
        room.setMinPrice(minPrice.getText().isEmpty()
                ? -1
                : Integer.parseInt(minPrice.getText()));
        room.setMaxPrice(maxPrice.getText().isEmpty()
                ? -1
                : Integer.parseInt(maxPrice.getText()));
        room.setRoomType(type.getText());
        room.setRoomView(view.getText());
        room.setBedsNO(
                numberOfBeds.getText().isEmpty()
                        ? -1
                        : Integer.parseInt(numberOfBeds.getText()));
        room.setBathRoomNO(
                numberOfBathrooms.getText().isEmpty()
                        ? -1
                        : Integer.parseInt(numberOfBathrooms.getText()));
        room.setBranchID(employee == null ? -1 : employee.getBranchID());
        room.setHotelID(employee == null ? -1 : employee.getHotelID());

        room.setHotelName(hotelName.getText());
        room.setHotelCity(hotelCity.getText());

        RoomSearch filterRooms = new RoomSearch();
        List<Room> searchedRooms = filterRooms.filterRooms(room);

        AvailableRooms controller = ControllerOperations.getController("../View/availableRooms.fxml");
        controller.setRooms(searchedRooms);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }
}
