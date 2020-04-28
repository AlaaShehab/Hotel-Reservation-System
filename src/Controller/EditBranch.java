package Controller;

import Model.Branch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBranch implements Initializable {

    private ManagerHome managerHomeController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        managerHomeController = ControllerOperations.getController("../View/managerHome.fxml");
        Branch branch = managerHomeController.getSelectedBranch();
    }
    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/managerHome.fxml", event);
    }
    @FXML
    private void editBranchHandler (ActionEvent event) throws Exception{

    }
}
