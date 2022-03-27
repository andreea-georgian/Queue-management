package BusinessLogic;

import Model.Server;
import Model.Task;
import View.View;

import java.io.File;
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
    SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private View theView;
    private File file;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfServers, int numberOfClients, View theView) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.theView = theView;
        file = new File("LogOfEvents.txt");
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
        generatedTasks = new ArrayList<>();
        generateNRandomTasks(numberOfClients);
    }

    void generateNRandomTasks(int n) {
        Random r = new Random();
        Task client;
        for (int i = 0; i < n; i++) {
            int nr1 = r.nextInt(maxArrivalTime);
            int nr2 = r.nextInt(maxProcessingTime);
            while (nr1 < minArrivalTime)
                nr1 = r.nextInt(maxArrivalTime);
            while (nr2 < minProcessingTime)
                nr2 = r.nextInt(maxProcessingTime);
            client = new Task(i + 1, nr1, nr2);
            generatedTasks.add(client);
        }
        generatedTasks.sort(Comparator.comparing(Task::getArrivalTime));
    }

    public void showWaitingTasks() {
        theView.asteptare.setText("");
        for (Task t : generatedTasks)
            theView.asteptare.setText(theView.asteptare.getText() + "(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + ")\n");
        for (Task t : generatedTasks)
            theView.asteptare.setText(theView.asteptare.getText() + "(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + ")\n");
    }

    public void showTasksInQueues(int currentTime, List<Server> servers) {
        theView.cozi.setText("Current Time: " + currentTime + "\n");
        for (int i = 0; i < servers.size(); i++) {
            theView.cozi.setText(theView.cozi.getText() + "Coada " + i + ": ");
            for (Task t : servers.get(i).getTasks()) {
                theView.cozi.setText(theView.cozi.getText() + "(" + t.getId() + ", " + t.getArrivalTime() + ", " + t.getServiceTime() + "); ");
            }
            theView.cozi.setText(theView.cozi.getText() + "\n");
        }
    }

    public void run() {
        int currentTime = 0;
        while (currentTime <= timeLimit) {
            while (generatedTasks.size() != 0 && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                generatedTasks.remove(0);
            }
            showWaitingTasks();
            showTasksInQueues(currentTime, scheduler.getServers());
            scheduler.deleteTasks();
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
