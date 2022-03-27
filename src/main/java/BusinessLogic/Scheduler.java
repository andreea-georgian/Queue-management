package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    //private int nrServers, maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int nrServers, int maxTasksPerServer) {
        servers = new ArrayList<>();
        Server server;
        for (int i = 0; i < nrServers; i++) {
            server = new Server(maxTasksPerServer);
            Thread t = new Thread(server);
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

    public void deleteTasks() {
        for (int i = 0; i < servers.size(); i++) {
            if (!servers.get(i).getTasks().isEmpty()){
                if (servers.get(i).getTasks().peek().getServiceTime() == 1)
                    servers.get(i).getTasks().remove();
                else
                    servers.get(i).getTasks().peek().setServiceTime(servers.get(i).getTasks().peek().getServiceTime() - 1);
            }
        }
    }
}
