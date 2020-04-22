package Controller;

import Model.Branch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BranchInfo implements Initializable {

    @FXML
    TextField hotelName;
    @FXML
    TextField branchLocation;

    private ManagerHome managerHomeController;
    private FXMLLoader loader;
    private Branch branch;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/managerHome.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        managerHomeController = loader.getController();
        branch = managerHomeController.getSelectedBranch();

        hotelName.setText("Birnando");
        branchLocation.setText(branch.getCity());
    }
    @FXML
    private void addEmplHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/addEmployee.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/managerHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

}
