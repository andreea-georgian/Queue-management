package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy implements Strategy{
    public void addTask(List<Server> servers, Task task) {
        int min = servers.get(0).getWaitingTime();
        for (Server s : servers)
            if (min > s.getWaitingTime())
                min = s.getWaitingTime();
        int ok = 0;
        for (Server s : servers)
            if (min == s.getWaitingTime() && ok == 0) {
                s.addTask(task);
                ok = 1;
            }
    }
}
