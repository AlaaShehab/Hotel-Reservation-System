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

    @FXML TextField hotelName;
    @FXML TextField branchLocation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ManagerHome managerHomeController = ControllerOperations.getController("../View/managerHome.fxml");
        Branch branch = managerHomeController.getSelectedBranch();

        hotelName.setText("Birnando");
        branchLocation.setText(branch.getCity());
    }
    @FXML
    private void addEmplHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/addEmployee.fxml", event);
    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/managerHome.fxml", event);
    }

}
