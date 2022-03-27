package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private int waitingPeriod;

    public Server(int maxNoTasksPerServer) {
        tasks = new ArrayBlockingQueue<>(maxNoTasksPerServer);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod += newTask.getServiceTime();
    }

    public void run() {
        while(true) {
            try {
                Task nextTask = tasks.take();
                Thread.sleep(nextTask.getServiceTime());
                waitingPeriod -= nextTask.getServiceTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public int getWaitingPeriod() { return waitingPeriod; }
}
