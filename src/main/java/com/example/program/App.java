package com.example.program;
import com.example.util.ControllerData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    public static <T> Stage getStage(String name, T data) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(name));

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );

        if (data != null) {
            ControllerData<T> controller = loader.getController();
            controller.initData(data);
        }
        return stage;
    }

    public static <T> Stage openWindow(String name, T data) throws IOException {
        Stage stage = getStage(name, data);
        stage.show();
        return stage;
    }

    public static <T> Stage openWindowAndWait(String name, T data) throws IOException {
        Stage stage = getStage(name, data);
        stage.showAndWait();
        return stage;
    }
}