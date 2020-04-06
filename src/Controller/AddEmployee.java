package Controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployee implements Initializable {
    @FXML private Button submit;
    @FXML private Button back;
    @FXML private PasswordField password ;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/addEmployee.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

    }
    @FXML
    private submitADDEmployee(){

    }
    @FXML
    private goBackToHomePage(){

    }

}

