package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeStrategy implements Strategy{
    public void addTask(List<Server> servers, Task task) {
        int min = servers.get(0).getWaitingPeriod();
        for (Server s : servers)
            if (min > s.getWaitingPeriod())
                min = s.getWaitingPeriod();
        int ok = 0;
        for (Server s : servers)
            if (min == s.getWaitingPeriod() && ok == 0) {
                s.addTask(task);
                ok = 1;
            }
    }
}
