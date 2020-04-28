package Controller;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    private static final String MSG = "Wrong email and password combination";

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField phone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backHandler(ActionEvent actionEvent) throws IOException {
        ControllerOperations.loadPage("../View/welcome.fxml", actionEvent);
    }

    public void signupHandler(ActionEvent actionEvent) throws IOException {
        if (!InputValidator.validName(firstName)
            || !InputValidator.validName(lastName)
            || !InputValidator.validPassword(password)
            || !InputValidator.validMobileNo(phone)
            || !InputValidator.validEmail(email)) {
            PopUpMessages.errorMsg(MSG);
        }
        UserActivity activity = new UserActivity();
        User user = new User();
        user.setEmail(email.getText());
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
        user.setNumberOfReservs(0);
        user.setPhoneNumber(phone.getText());
        user.setPassword(password.getText());

        try {
            boolean addedToDB = activity.signUp(user);
            SignIn signInController = ControllerOperations.getSignInController();
            signInController.setUser(addedToDB ? user : null);
        } catch (SQLException e) {
            PopUpMessages.errorMsg(MSG);
            return;
        }
        ControllerOperations.loadPage("../View/userHome.fxml", actionEvent);
    }
}
