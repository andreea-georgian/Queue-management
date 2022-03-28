package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int nrServers, int maxTasksPerServer) {
        servers = new ArrayList<>();
        Server server;
        for (int i = 0; i < nrServers; i++) {
            server = new Server(maxTasksPerServer);
            Thread t = new Thread(server);
            t.start();
            servers.add(server);
        }
    }

    void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ShortestQueueStrategy();
        if (policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new TimeStrategy();
    }

    void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }

    public void calculateWaitingTime() {
        for (Server s : servers) {
            s.setWaitingTime(0);
            for (Task t : s.getTasks()) {
                s.setWaitingTime(s.getWaitingTime() + t.getServiceTime());
            }
        }
    }

    public void decrementServiceTime() {
        for (Server s : servers) {
            if (!s.getTasks().isEmpty())
                s.getTasks().peek().setServiceTime(s.getTasks().peek().getServiceTime() - 1);
        }
    }
}
