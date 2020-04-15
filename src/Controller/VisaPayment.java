package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VisaPayment {

    @FXML private Button closeBtn;
    @FXML private TextField credircardNo;
    @FXML private TextField securityNo;
    @FXML private TextField expDate;

    @FXML
    private void closeWindowHandler (ActionEvent event) throws Exception{
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void verifyHanlder (ActionEvent event) throws Exception{

        if (!InputValidator.validCardNumber(credircardNo)
                || !InputValidator.validCVV(securityNo)
                || !InputValidator.validExpirationDate(expDate)) {
            PopUpMessages.errorMsg("Invalid Card Details!");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                "../View/reservationDetails.fxml"));
        loader.load();
        ReservationDetails controller = loader.getController();
        controller.setAlreadyPaid(true);

        PopUpMessages.successMsg("Payment is Success!");

        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }
}
