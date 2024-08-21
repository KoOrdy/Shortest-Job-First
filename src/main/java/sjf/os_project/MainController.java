package sjf.os_project;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button submit;

    @FXML
    private TextField TextField0;

    private static int NumOfProcesses;


    public int getNumOfProcessesValue() {
        return NumOfProcesses;
    }


    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        NumOfProcesses = getNumOfProcesses(TextField0.getText());

        if (NumOfProcesses != -1) {
            FXMLLoader inputsLoader = new FXMLLoader(getClass().getResource("Inputs.fxml"));
            Parent inputsRoot = inputsLoader.load();
            InputsController inputsController = inputsLoader.getController();
            inputsController.setNumOfProcesses(NumOfProcesses);

            FXMLLoader ResultsLoader = new FXMLLoader(getClass().getResource("Results.fxml"));
            Parent ResultsRoot = ResultsLoader.load();
            ResultsController ResultsController = ResultsLoader.getController();
            ResultsController.setNumOfProcesses(NumOfProcesses);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(inputsRoot);

            stage.setScene(scene);
            stage.show();
        }
    }

    public int getNumOfProcesses(String input) {
        try {
            NumOfProcesses = Integer.parseInt(input);
            if (NumOfProcesses <= 0) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a positive integer.");
                return -1;
            }
            return NumOfProcesses;
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid integer.");
            return -1;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}