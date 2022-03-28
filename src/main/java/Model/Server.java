package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private BlockingQueue<Integer> waitingPeriod;
    private int waitingTime;

    public Server(int maxNoTasksPerServer) {
        tasks = new ArrayBlockingQueue<>(maxNoTasksPerServer);
        waitingPeriod = new ArrayBlockingQueue<>(maxNoTasksPerServer);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.add(waitingTime);
    }

    public void run() {
        while(true) {
            Task nextTask;
            if (!tasks.isEmpty()) {
                try {
                    nextTask = tasks.peek();
                    Thread.sleep(nextTask.getServiceTime() * 1000);
                    tasks.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public BlockingQueue<Integer> getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
