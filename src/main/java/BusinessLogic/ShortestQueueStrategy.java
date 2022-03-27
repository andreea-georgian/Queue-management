package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy{
    public void addTask(List<Server> servers, Task task) {
        int min = servers.get(0).getTasks().size();
        for (Server s : servers) {
            if (s.getTasks().size() < min)
                min = s.getTasks().size();
        }
        int ok = 0;
        for (Server s : servers) {
            if (s.getTasks().size() == min && ok == 0) {
                s.addTask(task);
                ok = 1;
            }
        }
    }
}
