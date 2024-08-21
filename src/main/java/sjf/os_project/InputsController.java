package sjf.os_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class InputsController {
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

    @FXML
    private TextField arrivalTimeField;

    @FXML
    private TextField burstTimeField;

    @FXML
    private Text processText;

    @FXML
    private Button next;

    private int numOfProcesses;
    private int[] arrivalTimes;
    private int[] burstTimes;
    private int clickCounter = 0;
    private int arrivalIndex = 0;
    private int burstIndex = 0;

    public void setNumOfProcesses(int numOfProcesses) {
        this.numOfProcesses = numOfProcesses;
        arrivalTimes = new int[numOfProcesses];
        burstTimes = new int[numOfProcesses];
    }


    @FXML
    private void HandleNext(ActionEvent event) {
        if (!validateInput()) {
            return;
        }

        if (clickCounter < numOfProcesses - 1) {
            getArrivalTimes();
            getBurstTimes();
            clickCounter++;
            updateProcessIndication();
            arrivalTimeField.clear();
            burstTimeField.clear();
        } else if (clickCounter == numOfProcesses - 1) {
            getArrivalTimes();
            getBurstTimes();
            clickCounter++;
            updateProcessIndication();
            arrivalTimeField.clear();
            burstTimeField.clear();
            try {
                FXMLLoader classLoader = new FXMLLoader(getClass().getResource("Results.fxml"));
                Parent root = classLoader.load();

                ResultsController controller = classLoader.getController();
                controller.setArrival(arrivalTimes);
                controller.setBurst(burstTimes);
                controller.initialize();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProcessIndication() {
        Font font = Font.font("Arial", 24);
        Color textColor = Color.BLACK;
        if (processText != null) {
            AnchorPane.getChildren().remove(processText);
        }
        processText = new Text("Enter data for process " + (clickCounter + 1));
        processText.setFont(font);
        processText.setFill(textColor);
        processText.setLayoutX(160);
        processText.setLayoutY(90);
        AnchorPane.getChildren().add(processText);
    }

    public int[] getArrivalTimes() {
        String arrivalTime = arrivalTimeField.getText();
        String[] parts = arrivalTime.split("\\s+");
        for (int i = 0; i < parts.length && arrivalIndex < arrivalTimes.length; i++) {
            arrivalTimes[arrivalIndex] = Integer.parseInt(parts[i]);
            arrivalIndex++;
        }
        return arrivalTimes;
    }
    public int[] getBurstTimes() {
        String burstTime = burstTimeField.getText();
        String[] parts = burstTime.split("\\s+");
        for (int i = 0; i < parts.length && burstIndex < burstTimes.length; i++) {
            burstTimes[burstIndex] = Integer.parseInt(parts[i]);
            burstIndex++;
        }
        return burstTimes;
    }

    private boolean validateInput() {
        if (arrivalTimeField.getText().isEmpty() || burstTimeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter Arrival Time and Burst Time.");
            return false;
        }

        try {
            int arrivalTime = Integer.parseInt(arrivalTimeField.getText());
            int burstTime = Integer.parseInt(burstTimeField.getText());

            if (arrivalTime < 0 || burstTime <= 0) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter non-negative values for Arrival Time and Burst Time.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numeric values for Arrival Time and Burst Time.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}