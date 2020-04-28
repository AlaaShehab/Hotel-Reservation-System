package Controller;

import Model.Employee;
import Model.ManageDataBase;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignIn implements Initializable {

    @FXML private CheckBox employeeLogin;
    @FXML private TextField email;
    @FXML private PasswordField password;

    private static User user;
    private static Employee employee;
    private static final String MSG = "Wrong email and password combination";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void signInHandler (ActionEvent event) throws Exception{
        if (!InputValidator.validEmail(email) || !InputValidator.validPassword(password)) {
            PopUpMessages.errorMsg(MSG);
            return;
        }
        if (employeeLogin.isSelected()) {
            loginEmployee(event);
            return;
        }
        loginUser(event);
    }

    private void loginEmployee (ActionEvent event) throws IOException {
        ManageDataBase activity = new ManageDataBase();
        Employee employee = null;
        try {
            employee = activity.signIN(email.getText(), password.getText());
        } catch (SQLException e) {
            PopUpMessages.errorMsg(MSG);
            return;
        }
        if (employee == null) {
            PopUpMessages.errorMsg(MSG);
            return;
        }
        setEmployee(employee);
        if (employee.isManager()) {
            ControllerOperations.loadPage("../View/managerHome.fxml", event);
        } else {
            ControllerOperations.loadPage("../View/staffHome.fxml", event);
        }
    }

    private void loginUser (ActionEvent event) throws IOException {
        UserActivity activity = new UserActivity();
        User user = null;
        try {
            user = activity.signIN(email.getText(), password.getText());
        } catch (SQLException e) {
            PopUpMessages.errorMsg(MSG);
        }
        if (user == null) {
            PopUpMessages.errorMsg(MSG);
            return;
        }
        setUser(user);
        ControllerOperations.loadPage("../View/userHome.fxml", event);
    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/welcome.fxml", event);
    }

    public User getUser () {
        return user;
    }
    public void setUser (User user) {
        this.user = user;
    }
    public Employee getEmployee () {
        return employee;
    }
    public void setEmployee (Employee employee) {
        this.employee = employee;
    }

}
