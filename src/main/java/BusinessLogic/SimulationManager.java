package BusinessLogic;

import Model.Server;
import Model.Task;
import View.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    int timeLimit;
    int maxProcessingTime;
    int minProcessingTime;
    int maxArrivalTime;
    int minArrivalTime;
    int numberOfServers;
    int numberOfClients;
    SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private View theView;
    private FileWriter file;
    private SimulationResults simulationResults;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfServers, int numberOfClients, SelectionPolicy selectionPolicy, View theView) throws IOException {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.selectionPolicy = selectionPolicy;
        this.theView = theView;
        file = new FileWriter("LogOfEvents.txt");
        simulationResults = new SimulationResults();
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
        generatedTasks = new ArrayList<>();
        generateNRandomTasks(numberOfClients);
    }

    void generateNRandomTasks(int n) {
        Random r = new Random();
        Task client;
        for (int i = 0; i < n; i++) {
            int nr1 = r.nextInt(maxArrivalTime + 1);
            int nr2 = r.nextInt(maxProcessingTime + 1);
            while (nr1 < minArrivalTime)
                nr1 = r.nextInt(maxArrivalTime);
            while (nr2 < minProcessingTime)
                nr2 = r.nextInt(maxProcessingTime);
            client = new Task(i + 1, nr1, nr2);
            generatedTasks.add(client);
        }
        generatedTasks.sort(Comparator.comparing(Task::getArrivalTime));
    }

    public void displayWaitingTasks(int currentTime) throws IOException {
        theView.asteptare.setText("");
        file.append("Timp " + currentTime + "\nClienti in asteptare: \n");
        for (Task t : generatedTasks) {
            theView.asteptare.setText(theView.asteptare.getText() + "(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + ")\n");
            file.append("(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + ")\n");
        }
    }

    public void displaySimulationResults(int serviceTime) throws IOException {
        file.append("Timp mediu de asteptare: " + ((double)simulationResults.getWaitingTime(scheduler) / numberOfClients));
        file.append("\nTimp mediu de servire: " + ((double)serviceTime / numberOfClients));
        file.append("\nOra de varf: " + simulationResults.getPeekHour());
    }

    public void displayTasksInQueues(int currentTime, List<Server> servers) throws IOException {
        theView.cozi.setText("Current Time: " + currentTime + "\n");
        for (int i = 0; i < servers.size(); i++) {
            theView.cozi.setText(theView.cozi.getText() + "Coada " + i + ": ");
            file.append("Coada " + i + ": ");
            for (Task t : servers.get(i).getTasks()) {
                theView.cozi.setText(theView.cozi.getText() + "(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + "); ");
                file.append("(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + "); ");
            }
            theView.cozi.setText(theView.cozi.getText() + "\n");
            file.append("\n");
        }
        file.append("\n");
    }

    public void run() {
        int currentTime = 0, serviceTime;
        serviceTime = simulationResults.getServiceTime(generatedTasks);
        while (currentTime <= timeLimit) {
            while (generatedTasks.size() != 0 && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                generatedTasks.remove(0);
                scheduler.calculateWaitingTime();
            }
            try {
                displayWaitingTasks(currentTime);
                displayTasksInQueues(currentTime, scheduler.getServers());
            } catch (IOException e) {
                e.printStackTrace();
            }
            simulationResults.calculatePeekHour(scheduler, currentTime);
            scheduler.decrementServiceTime();
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            displaySimulationResults(serviceTime);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
