package Controller;

import Model.ManageDataBase;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField numberOfReservations;

    private static User user;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = ((SignIn) ControllerOperations.getController("../View/signIn.fxml")).getUser();
        numberOfReservations.setDisable(true);
    }

    public void closeWindowHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/userHome.fxml", actionEvent);
    }

    public void EditProfileHandler(ActionEvent actionEvent) throws IOException {
        if (!validInput()) {
            PopUpMessages.errorMsg("Wrong profile data");
            return;
        }
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
        user.setPassword(password.getText());
        user.setEmail(email.getText());
        user.setPhoneNumber(phone.getText());

        UserActivity activity = new UserActivity();
        if (!activity.editUserInfo(user)) {
            PopUpMessages.errorMsg("Wrong profile data");
            return;
        }
        PopUpMessages.successMsg("Your profile was updated");
        ((SignIn) ControllerOperations.getController("../View/signIn.fxml")).setUser(user);
        closeWindowHandler(actionEvent);
    }
    private boolean validInput () {
        return InputValidator.validEmail(email) && InputValidator.validPassword(password)
                && InputValidator.validName(firstName) && InputValidator.validName(lastName)
                && InputValidator.validMobileNo(phone);
    }
}
