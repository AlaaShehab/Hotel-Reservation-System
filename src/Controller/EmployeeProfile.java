package Controller;

import Model.ManageDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import Model.Employee;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeProfile implements Initializable {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private TextField startDate;

    private SignIn signInController;
    private static Employee emplyee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signInController = ControllerOperations.getController("../View/signIn.fxml");
        emplyee = signInController.getEmployee();

        init();
    }

    private void init() {
        firstName.setText(emplyee.getFirstName());
        lastName.setText(emplyee.getLastName());
        password.setText(emplyee.getPassword());
        email.setText(emplyee.getEmail());
        phone.setText(emplyee.getPhoneNo());
        address.setText(emplyee.getAddress());
        startDate.setText(emplyee.getStartDate());
    }

    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage(
                emplyee.isManager()
                        ? "../View/managerHome.fxml"
                        : "../View/staffHome.fxml", event);
    }

    @FXML
    private void EditProfileHandler (ActionEvent event) throws Exception{
        if (!validInput()) {
            PopUpMessages.errorMsg("Wrong profile data");
            return;
        }
        emplyee.setFirstName(firstName.getText());
        emplyee.setLastName(lastName.getText());
        emplyee.setPassword(password.getText());
        emplyee.setEmail(email.getText());
        emplyee.setPhoneNo(phone.getText());
        emplyee.setAddress(address.getText());
        emplyee.setStartDate(startDate.getText());

        ManageDataBase activity = new ManageDataBase();
        if (!activity.editEmployeeInfo(emplyee)) {
            PopUpMessages.errorMsg("Wrong profile data");
            return;
        }
        PopUpMessages.successMsg("Your profile was updated");
        signInController.setEmployee(emplyee);
        closeWindowHandler(event);
    }

    private boolean validInput () {
        return InputValidator.validEmail(email) && InputValidator.validPassword(password)
                && InputValidator.validName(firstName) && InputValidator.validName(lastName)
                && InputValidator.validMobileNo(phone);
    }
}
