package Controller;

import Model.Employee;
import Model.ManageDataBase;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        if (InputValidator.validEmail(email) || InputValidator.validPassword(password)) {
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
        Parent root = null;
        if (employee.isManager()) {
            root = FXMLLoader.load(getClass().getResource("../View/managerHome.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("../View/staffHome.fxml"));
        }
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    private void loginUser (ActionEvent event) throws IOException {
        ManageDataBase activity = new ManageDataBase();
        User user = null;
        //TODO milestone 3
//        try {
//            user = activity.signIN(email.getText(), password.getText())
//        } catch (SQLException e) {
//            errorMsg();
//        }
        if (user == null) {
            PopUpMessages.errorMsg(MSG);
            return;
        }
        setUser(user);
        //TODO edit in milestone 3
        Parent root = FXMLLoader.load(getClass().getResource("../View/welcome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/welcome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
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
