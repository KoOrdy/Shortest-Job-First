package sjf.os_project;

import java.io.IOException;

public class ResultsLogic {


    //attributes
    private int numOfProcesses;
    private int totalBurstTime;
    private int[][] processesDetails; //[Arrival time , Burst time]
    private int [] waitingTime;
    private int [] turnaroundTime;
    private int [] responseTime;
    private int [] remainingTime;
    private boolean[] checkCompleted;
    private int[] ganttChart;
    public int countProcesses;


    //intialize all attributes with number of processes
    public void setNumOfProcesses(int numOfProcesses){

        this.numOfProcesses = numOfProcesses;
        this.processesDetails = new int[this.numOfProcesses][2]; //[arrival , burst]
        this.waitingTime = new int[numOfProcesses];
        this.turnaroundTime = new int[numOfProcesses];
        this.responseTime = new int[numOfProcesses];
        this.remainingTime = new int[numOfProcesses];
        this.checkCompleted = new boolean[numOfProcesses];
        this.totalBurstTime = 0;

    }

    // set arrival & burst time of processes
    public void setProcessDetails(int[] arrivalTime , int[] burstTime){

        for (int i = 0 ; i < this.numOfProcesses ; i++){
            this.processesDetails[i][0] = arrivalTime[i];
            this.processesDetails[i][1] = burstTime[i];
            this.remainingTime[i] = burstTime[i];
            this.checkCompleted[i] = false;
            this.totalBurstTime += processesDetails[i][1];
        }

    }

    //shortest Job First Preemptive
    public void SJFP(){

        this.ganttChart = new int[this.totalBurstTime];

        int currentTime = 0;

        while (currentTime < this.totalBurstTime){

            int currentProcessIndex = -1;
            int shortestTime = this.totalBurstTime;//Assign the largest possible value to shortestTime

            // find the shortest job time process
            for (int i = 0; i < this.numOfProcesses ; i++){

                if (this.checkCompleted[i] == false && this.processesDetails[i][0] <= currentTime && this.remainingTime[i] < shortestTime) {

                    shortestTime = this.remainingTime[i]; //save the remaining time of the process to compare it with other processes
                    currentProcessIndex = i; //save the index of the shortest process

                    this.ganttChart[currentTime] = currentProcessIndex + 1 ; //save currect process at current second

                    //calculate response time if it is the first time the process is in the CPU
                    if(this.remainingTime[i] == this.processesDetails[i][1]){
                        this.responseTime[i] = currentTime - this.processesDetails[i][0]; //currentTime - arrivalTime
                    }
                }
            }

            //if it fails to find the shortest job time process , continue with the next second
            if(currentProcessIndex == -1){

                currentTime++;
                continue;

            }
            //if it successes , decrement this second from the remainingTime of the process
            else{

                this.remainingTime[currentProcessIndex]--;
                currentTime++;

            }

            //calculate waiting and turnaround time if the current process completed
            if(this.remainingTime[currentProcessIndex] == 0){

                this.checkCompleted[currentProcessIndex] = true;
                this.turnaroundTime[currentProcessIndex] = currentTime - processesDetails[currentProcessIndex][0];
                this.waitingTime[currentProcessIndex] = this.turnaroundTime[currentProcessIndex] - this.processesDetails[currentProcessIndex][1];

            }
        }
    }

    //calculate waiting time
    public double avgWaitingTime(){
        int totalWaitingTime = 0;

        for (int i = 0 ; i < this.numOfProcesses ; i++){
            totalWaitingTime += this.waitingTime[i];
        }

        double avgWaitingTime = (double)totalWaitingTime / this.numOfProcesses;
        return  avgWaitingTime;
    }

    //calculate avg turnaround time
    public double avgTurnaroundTime(){
        int totalturnaroundTime = 0;

        for (int i = 0 ; i < this.numOfProcesses ; i++) {
            totalturnaroundTime += this.turnaroundTime[i];
        }

        double avgTurnaroundTime = (double)totalturnaroundTime / this.numOfProcesses;
        return avgTurnaroundTime;
    }

    //calculate avg response time
    public double avgResponseTime(){
        int totalresponeTime = 0;

        for (int i = 0 ; i < this.numOfProcesses ; i++){
            totalresponeTime += this.responseTime[i];
        }

        double avgResponeTime = (double)totalresponeTime / this.numOfProcesses;
        return avgResponeTime;
    }

    //Getters
    int[][] getProcessesDetails(){
        return processesDetails;
    }

    int[] getWaitingTime(){
        return this.waitingTime;
    }

    int[] getTurnaroundTime(){
        return this.turnaroundTime;
    }

    int[] getResponseTime(){
        return this.responseTime;
    }

    int[] getGanttChart(){
        return this.ganttChart;
    }

    //calculate start & end for each process
    int[][] ganttChartStartEnd (){

        //count the size of two demisional array
        this.countProcesses = 0;

        if (this.totalBurstTime > 0) {
            this.countProcesses++;
        }

        for (int i = 1; i < this.totalBurstTime; i++) {
            if (this.ganttChart[i] != ganttChart[i - 1]) {
                this.countProcesses++;
            }
        }

        int[][] startEnd = new int[this.countProcesses][3]; //[processNumber , start , end]

        int currentProcess = this.ganttChart[0];
        int startTime = 0;
        int index = 0;

        for (int i = 1; i < this.totalBurstTime; i++) {
            if (this.ganttChart[i] != currentProcess) {
                startEnd[index][0] = currentProcess;
                startEnd[index][1] = startTime;
                startEnd[index][2] = i;
                index++;
                startTime = i;
                currentProcess = this.ganttChart[i];
            }
        }

        startEnd[index][0] = currentProcess;
        startEnd[index][1] = startTime;
        startEnd[index][2] = this.totalBurstTime;

        return startEnd ;
    }

}
