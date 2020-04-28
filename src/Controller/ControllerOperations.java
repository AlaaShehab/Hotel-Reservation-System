package Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class ControllerOperations {
    static  <T> T getController (String controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ControllerOperations.class.getResource(
                controller));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println("cannot load");
        }

        return loader.getController();
    }
    static void loadPage(String page, ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(ControllerOperations.class.getResource(page));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    static void loadPage(String page, Event event) throws IOException {
        Parent root = FXMLLoader.load(ControllerOperations.class.getResource(page));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
}
