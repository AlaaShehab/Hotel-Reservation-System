package Controller;

import Model.Branch;
import Model.Employee;
import Model.ManageDataBase;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerHome implements Initializable {

    @FXML private TableView branches;
    @FXML private TableColumn branchID;
    @FXML private TableColumn location;
    @FXML private Label managerName;
    @FXML private Label hotelName;
    @FXML private Label noBranches;

    @FXML private Button getBranchInfo;

    private static Branch selectedBranch;
    private static Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SignIn signInController = ControllerOperations.getController("../View/signIn.fxml");
        employee = signInController.getEmployee();
        init();
    }

    private void init () {
        branchID.setCellValueFactory(new PropertyValueFactory<Branch, String>("branchID"));
        location.setCellValueFactory(new PropertyValueFactory<Branch, String>("location"));

        ManageDataBase activity = new ManageDataBase();
        List<Branch> managerBranches = activity.getManagerBranches(employee.getEmployeeID());
        managerName.setText(employee.getFirstName() + " " + employee.getLastName());

        if (managerBranches.isEmpty()) {
            hotelName.setVisible(false);
            branches.setVisible(false);
            branchID.setVisible(false);
            location.setVisible(false);
            noBranches.setVisible(true);
            getBranchInfo.setDisable(true);
            return;
        }

        hotelName.setText(managerBranches.get(0).getHotelName());
        refresh(managerBranches);
        getBranchInfo.setOnAction(new CheckBranchListener());
    }

    public void refresh (List<Branch> managerBranches) {
        branches.setItems(FXCollections.observableList(new ArrayList<>()));
        branches.getItems().addAll(getTableData(managerBranches));
    }
    private ObservableList getTableData(List<Branch> managerBranches) {
        ObservableList data = FXCollections.observableList(managerBranches);
        return data;
    }

    @FXML
    private void backHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/managerHome.fxml", event);
    }

    private class CheckBranchListener implements EventHandler {
        @Override
        public void handle(Event event){
            //TODO handle if no item is selected (i.e. ix is out of range)
            int ix = branches.getSelectionModel().getSelectedIndex();
            if (ix < 0) {
                PopUpMessages.errorMsg("Please select a valid branch");
                return;
            }
            Branch branch = (Branch) branches.getSelectionModel().getSelectedItem();
            selectedBranch = branch;
            try {
                ControllerOperations.loadPage("../View/branchInfo.fxml", event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void addBranchHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/addBranch.fxml", event);
    }
    @FXML
    private void editProfileHandler (ActionEvent event) throws Exception{
        ControllerOperations.loadPage("../View/employeeProfile.fxml", event);
    }

    public Branch getSelectedBranch () {
        return selectedBranch;
    }
}
