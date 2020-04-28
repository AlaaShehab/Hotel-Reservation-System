package Controller;

import Model.Room;
import Model.RoomSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class UserHome implements Initializable {

    @FXML private TextField hotelName;
    @FXML private TextField hotelCity;
    @FXML private TextField hotelCountry;
    @FXML private TextField checkOut;
    @FXML private TextField checkIn;
    @FXML private TextField minPrice;
    @FXML private TextField maxPrice;

    private static List<Room> searchedRooms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void profileHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/userProfile.fxml", actionEvent);
    }

    public void reservationsHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/userReservations.fxml", actionEvent);
    }

    public void searchHandler(ActionEvent actionEvent) throws ParseException, IOException {
        if ((!InputValidator.validDate(checkIn) && checkIn.getText().isEmpty())
            || (!InputValidator.validDate(checkOut) && checkOut.getText().isEmpty())
            || !InputValidator.validInteger(minPrice)
            || !InputValidator.validInteger(maxPrice)) {
            PopUpMessages.errorMsg("Invalid search data");
        }
        Room room = new Room();
        room.setCheckINdate(checkIn.getText());
        room.setCheckOUTdate(checkOut.getText());
        room.setMinPrice(minPrice.getText().isEmpty() ?
                -1 :
                Integer.parseInt(minPrice.getText()));
        room.setMinPrice(maxPrice.getText().isEmpty() ?
                -1 :
                Integer.parseInt(maxPrice.getText()));
        room.setHotelName(hotelName.getText());
        room.setHotelCity(hotelCity.getText());
        room.setHotelCountry(hotelCountry.getText());

        RoomSearch search = new RoomSearch();
        searchedRooms = search.filterRooms(room);

        if (searchedRooms != null) {
            ControllerOperations.loadPage("../View/availableRooms.fxml", actionEvent);
        }
    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/welcome.fxml", actionEvent);
    }

    public List<Room> getSearchedRooms () {
        return searchedRooms;
    }
}
