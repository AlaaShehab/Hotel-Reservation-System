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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/addEmployee.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

    }
    @FXML
    private void submitNewEmployee() throws SQLException, ParseException {
        Employee e = new Employee();
        e.setFirstName(firstName.toString());
        e.setLastName(lastName.toString());
        e.setPassword(password.toString());
        //ToDo Email Validation
        e.setEmail(email.toString());
        e.setPhoneNo(phoneNumber.toString());
        mDB.addEmployee(e);
    }
    @FXML
    private void goBackToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/managerHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

}

