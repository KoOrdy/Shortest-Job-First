package sjf.os_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent mainRoot = mainLoader.load();

        Scene scene = new Scene(mainRoot);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SJF simulation");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}