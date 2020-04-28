package Controller;

import Model.Employee;
import Model.ManageDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddEmployee implements Initializable {

    @FXML private Button submit;
    @FXML private Button back;
    @FXML private PasswordField password ;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;

    private static ManageDataBase mDB = new ManageDataBase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void submitNewEmployee() throws SQLException, ParseException {
        Employee e = new Employee();
        e.setFirstName(firstName.toString());
        e.setLastName(lastName.toString());
        e.setPassword(password.toString());
        e.setEmail(email.toString());
        e.setPhoneNo(phoneNumber.toString());
        mDB.addEmployee(e);
    }
    @FXML
    private void goBackToHomePage(ActionEvent event) throws IOException {
        ControllerOperations.loadPage("../View/managerHome.fxml", event);
    }

}

