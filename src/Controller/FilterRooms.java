package Controller;

import Model.Employee;
import Model.Room;
import Model.RoomSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    public void backHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    public void filterHandler(ActionEvent actionEvent) throws IOException {
        if ((!InputValidator.validDate(checkInDate) && !checkInDate.getText().isEmpty())
                || (!InputValidator.validDate(checkOutDate) && !checkOutDate.getText().isEmpty())
                || !InputValidator.validPriceRange(minPrice, maxPrice)
                || !InputValidator.validDateRange(checkInDate, checkOutDate)
                || !InputValidator.validFilterNumber(numberOfBathrooms)
                || !InputValidator.validFilterNumber(numberOfBeds)) {
            PopUpMessages.errorMsg("Invalid Filter");
        }
        Room room = new Room();
        //TODO
//        roomSearch.setCheckinDate(checkInDate.getText());
//        roomSearch.setCheckOutDate(checkOutDate.getText());
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
        room.setBranchID(employee.getBranchID());
        room.setHotelID(employee.getHotelID());

        RoomSearch filterRooms = new RoomSearch();
        List<Room> searchedRooms = filterRooms.filterRooms(room);

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
