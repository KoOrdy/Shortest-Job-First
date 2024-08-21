package sjf.os_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class ResultsController {

    @FXML
    private VBox process;

    @FXML
    private VBox arrival;

    @FXML
    private VBox burst;

    @FXML
    private VBox waiting;

    @FXML
    private VBox response;

    @FXML
    private VBox turnaround;

    @FXML
    private Button next;
    @FXML
    private HBox aveResponse;

    @FXML
    private HBox aveTurnaround;

    @FXML
    private HBox aveWaitnig;

    @FXML
    private AnchorPane ancroId;

    private ResultsLogic resultsLogic;
    private static int numOfProcesses;
    private int[] arrivalTimes;
    private int[] burstTimes;
    private int[] waitingTimes;
    private int[] responseTimes;
    private int[] turnaroundTimes;
    private int[][] startEnd;
    private  double avgwaitingtime;
    private  double avgturnaroundtime;
    private  double avgresponsetime;

    public ResultsController() {
    }

    public void setNumOfProcesses(int numOfProcesses) {
        this.numOfProcesses = numOfProcesses;
    }
    public void setArrival(int [] arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }
    public void setBurst(int [] burstTimes) {
        this.burstTimes = burstTimes;
    }
    public void setWaiting(int [] waitingTimes) {
        this.waitingTimes = waitingTimes;
    }
    public void setResponse(int [] responseTimes) {
        this.responseTimes = responseTimes;
    }
    public void setTurnaround(int [] turnaroundTimes) {
        this.turnaroundTimes = turnaroundTimes;
    }
    public void setAvgwaitingtime(double avgwaitingtime){
        this.avgwaitingtime=avgwaitingtime;
    }
    public void setAvgturnaroundtime(double avgturnaroundtime){
        this.avgturnaroundtime=avgturnaroundtime;
    }
    public void setAvgresponsetime (double avgresponsetime){
        this.avgresponsetime=avgresponsetime;
    }
    @FXML
    private void handleNext(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("GanttChart.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();
        ancroId.getChildren().clear();
        startEnd = resultsLogic.ganttChartStartEnd();
        HBox box = new HBox();
        box.setLayoutX(100);
        box.setLayoutY(150);
        for(int i = 0 ; i < resultsLogic.countProcesses ; i++){
            Label label = new Label("P" + startEnd[i][0] + "\n"+startEnd[i][1] + "-->" + startEnd[i][2]);
            label.setMinHeight(40);
            label.setMinWidth((startEnd[i][2] - startEnd[i][1]) * 30) ;
            label.setStyle("-fx-text-fill: blue; -fx-border-color: black; -fx-border-width: 2; -fx-font-size: 20; -fx-alignment: center;");
            box.getChildren().add(label);
        }
        ancroId.getChildren().add(box);
    }

    public void displayProcess() {
        Font font = Font.font("system", 14);
        Color textColor = Color.BLACK;
        double initialY = 4;
        for (int i = 1; i <= numOfProcesses; i++) {
            HBox hbox = new HBox();
            Label label = new Label("Process " + i);
            label.setFont(font);
            label.setTextFill(textColor);
            hbox.getChildren().add(label);
            hbox.setLayoutY(initialY + (i - 1) * 100);
            process.getChildren().add(hbox);
        }
    }

    public void displayArrival() {
        Font font = Font.font("system", 14);
        Color textColor = Color.BLACK;
        double initialY = 4;
        for (int i = 0; i < arrivalTimes.length; i++) {
            HBox hbox = new HBox();
            Label label = new Label(String.valueOf(arrivalTimes[i]));
            label.setFont(font);
            label.setTextFill(textColor);
            hbox.getChildren().add(label);
            hbox.setLayoutY(initialY + i * 100);
            arrival.getChildren().add(hbox);
        }
    }




    public void displayBurst() {
        Font font = Font.font("system", 14);
        Color textColor = Color.BLACK;
        for (int i = 0; i < burstTimes.length; i++) {
            HBox hbox = new HBox();
            Label label = new Label(String.valueOf(burstTimes[i]));
            label.setFont(font);
            label.setTextFill(textColor);
            hbox.getChildren().add(label);
            burst.getChildren().add(hbox);
        }
    }

    public void displayWaiting() {

        Font font = Font.font("system", 14);
        Color textColor = Color.BLACK;
        for (int i = 0; i < waitingTimes.length; i++) {
            HBox hbox = new HBox();
            Label label = new Label(String.valueOf(waitingTimes[i]));
            label.setFont(font);
            label.setTextFill(textColor);
            hbox.getChildren().add(label);
            waiting.getChildren().add(hbox);
        }
    }
    public void displayTurnaround() {
        Font font = Font.font("system", 14);
        Color textColor = Color.BLACK;
        double initialY = 4;
        for (int i = 0; i < turnaroundTimes.length; i++) {
            HBox hbox = new HBox();
            Label label = new Label(String.valueOf(turnaroundTimes[i]));
            label.setFont(font);
            label.setTextFill(textColor);
            hbox.getChildren().add(label);
            hbox.setLayoutY(initialY + i * 100);
            turnaround.getChildren().add(hbox);
        }
    }

    public void displayResponse() {
        Font font = Font.font("system", 14);
        Color textColor = Color.BLACK;
        double initialY = 4;
        for (int i = 0; i < responseTimes.length; i++) {
            HBox hbox = new HBox();
            Label label = new Label(String.valueOf(responseTimes[i]));
            label.setFont(font);
            label.setTextFill(textColor);
            hbox.getChildren().add(label);
            hbox.setLayoutY(initialY + i * 100);
            response.getChildren().add(hbox);
        }
    }

    public void displayAve(){
        Label avgWaitingLabel = new Label(String.valueOf(avgwaitingtime));
        Label avgResponseLabel = new Label(String.valueOf(avgresponsetime));
        Label avgTurnaroundLabel = new Label(String.valueOf(avgturnaroundtime));

        aveWaitnig.getChildren().add(avgWaitingLabel);
        aveResponse.getChildren().add(avgResponseLabel);
        aveTurnaround.getChildren().add(avgTurnaroundLabel);
    }

    public void initialize() throws IOException {
        if (arrivalTimes == null || burstTimes == null) {
            return;
        }

        resultsLogic = new ResultsLogic();
        resultsLogic.setNumOfProcesses(numOfProcesses);
        resultsLogic.setProcessDetails(arrivalTimes, burstTimes);
        resultsLogic.SJFP();
        waitingTimes = resultsLogic.getWaitingTime();
        turnaroundTimes = resultsLogic.getTurnaroundTime();
        responseTimes = resultsLogic.getResponseTime();
        avgwaitingtime=resultsLogic.avgWaitingTime();
        avgturnaroundtime=resultsLogic.avgTurnaroundTime();
        avgresponsetime=resultsLogic.avgResponseTime();

        displayProcess();
        displayArrival();
        displayBurst();
        displayWaiting();
        displayResponse();
        displayTurnaround();
        displayAve();
    }

    public void setGanttChart(){

    }
}