package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class SimulationResults {
    private int waitingTime;
    private int serviceTime;
    private int peekHour;
    private int maxNumberOfClients;

    public void calculatePeekHour(Scheduler scheduler, int currentTime) {
        int aux = 0;
        for(Server s : scheduler.getServers())
            aux += s.getTasks().size();
        if (aux > maxNumberOfClients) {
            peekHour = currentTime;
            maxNumberOfClients = aux;
        }
    }

    public int getWaitingTime(Scheduler scheduler) {
        for (Server s : scheduler.getServers()) {
            for (Integer i : s.getWaitingPeriod()) {
                waitingTime += i;
            }
        }
        return waitingTime;
    }

    public int getServiceTime(List<Task> generatedTasks) {
        for(Task t : generatedTasks)
            serviceTime += t.getServiceTime();
        return serviceTime;
    }

    public int getPeekHour() {
        return peekHour;
    }
}
