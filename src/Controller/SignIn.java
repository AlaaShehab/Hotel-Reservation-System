package Controller;

import Model.Employee;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignIn implements Initializable {

    @FXML private CheckBox employeeLogin;
    @FXML private TextField email;
    @FXML private TextField password;

    private static User user;
    private static Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void signInHandler (ActionEvent event) throws Exception{

        if (employeeLogin.isSelected()) {
            loginEmployee(event);
            return;
        }
        loginUser(event);
    }

    private void loginEmployee (ActionEvent event) throws IOException {
        EmployeeActivities activity = new EmployeeActivities();
        if (!activity.employeeExists(email.getText(), password.getText())) {
            errorMsg();
        }
        setEmployee(activity.getEmployee());
        Parent root = null;
        if (employee.isManager()) {
            FXMLLoader.load(getClass().getResource("View/managerHome.fxml"));
        } else {
            FXMLLoader.load(getClass().getResource("View/staffHome.fxml"));
        }
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    private void loginUser (ActionEvent event) throws IOException {
        UserActivities activity = new UserActivities();
        if (!activity.userExists(email.getText(), password.getText())) {
            errorMsg();
        }
        setUser(activity.getUser());
        //TODO edit in milestone 3
        Parent root = FXMLLoader.load(getClass().getResource("View/welcome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    private void errorMsg () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Signing in");
        alert.setHeaderText("Wrong email and password combination");
        alert.setContentText(null);

        alert.showAndWait();
    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/welcome.fxml"));
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
