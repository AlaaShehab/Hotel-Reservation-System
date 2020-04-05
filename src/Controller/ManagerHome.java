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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/signIn.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        SignIn signInController = loader.getController();
        employee = signInController.getEmployee();
        init();
    }

    private void init () {
        branchID.setCellValueFactory(new PropertyValueFactory<Branch, String>("branchID"));
        location.setCellValueFactory(new PropertyValueFactory<Branch, String>("location"));

        ManageDataBase activity = new ManageDataBase();
        //TODO edit this
        //List<Branch> managerBranches = activity.getBranches(employee.getEmployeeID());
        List<Branch> managerBranches = new ArrayList<>();
        //dump test TODO remove this
        Branch b = new Branch();
        b.setBranchID(1);
        b.setGarage(true);
        b.setLocation("Milano");
        b.setCarRental(true);
        b.setRating(3);

        managerBranches.add(b);
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
        Parent root = FXMLLoader.load(getClass().getResource("../View/managerHome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    private class CheckBranchListener implements EventHandler {
        @Override
        public void handle(Event event){
            //TODO handle if no item is selected (i.e. ix is out of range)
            int ix = branches.getSelectionModel().getSelectedIndex();
            Branch branch = (Branch) branches.getSelectionModel().getSelectedItem();
            selectedBranch = branch;

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../View/branchInfo.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        }
    }
    @FXML
    private void addBranchHandler (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/addBranch.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
}
