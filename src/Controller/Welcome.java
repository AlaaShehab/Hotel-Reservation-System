package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {

    private DBManager dbManager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbManager = DBManager.getInstance();
        FileInputStream inputstream = null;
        try {
            //inputstream = new FileInputStream("D:\\Java Workspace\\Hotel Reservation System\\src\\Hotel.jpg");
        } catch (Exception e) {

        }
        Image image = new Image (inputstream);

        //TODO set background
    }

    @FXML
    private void signInHandler (ActionEvent event) throws Exception {
        Parent signIn_parent = FXMLLoader.load(getClass().getResource("View/signIn.fxml"));
        Scene signUp_scene = new Scene(signIn_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signUp_scene);
        app_stage.show();
    }

    @FXML
    private void signUpHandler (ActionEvent event) throws Exception {
        Parent signUp_parent = FXMLLoader.load(getClass().getResource("View/signUp.fxml"));
        Scene signUp_scene = new Scene(signUp_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signUp_scene);
        app_stage.show();
    }
}
