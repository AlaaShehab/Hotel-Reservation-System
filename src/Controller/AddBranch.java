package Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBranch implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/addBranch.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }
    }
}
