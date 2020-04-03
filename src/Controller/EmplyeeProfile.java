package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Model.Employee;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmplyeeProfile implements Initializable {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private TextField startDate;

    private FXMLLoader loader;
    private SignIn signInController;
    private static Employee emplyee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "View/signIn.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        signInController = loader.getController();
        emplyee = signInController.getEmployee();

        init();
    }

    private void init() {
        firstName.setText(emplyee.getFirstName());
        lastName.setText(emplyee.getLastName());
        password.setText(emplyee.getPassword());
        email.setText(emplyee.getEmail());
        phone.setText(emplyee.getPhoneNumber());
        address.setText(emplyee.getAddress());
        startDate.setText(emplyee.startDate());
    }

    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/managerHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void EditProfileHandler (ActionEvent event) throws Exception{

        emplyee.setFirstName(firstName.getText());
        emplyee.setLastName(lastName.getText());
        emplyee.setPassword(password.getText());
        emplyee.setEmail(email.getText());
        emplyee.setPhoneNumber(phone.getText());
        emplyee.setAddress(address.getText());
        emplyee.startDate(startDate.getText());

        UsersActivitie activity = new UsersActivitie();

        if (!activity.editEmployeeProfile(emplyee)) {
            errorMsg ();
        }
        signInController.setEmployee(emplyee);
        closeWindowHandler(event);
    }

    private void errorMsg () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong profile data");
        alert.setContentText(null);

        alert.showAndWait();
    }
}