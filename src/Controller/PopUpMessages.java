package Controller;

import javafx.scene.control.Alert;

class PopUpMessages {

    static void errorMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(msg);
        alert.setContentText(null);

        alert.showAndWait();
    }

    static void successMsg (String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(msg);
        alert.setContentText(null);

        alert.showAndWait();
    }
}
