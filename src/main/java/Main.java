import BusinessLogic.SimulationManager;
import View.View;

public class Main {
    public static void main (String[] args) {
        //SimulationManager gen = new SimulationManager();
        View theView = new View();
        Controller theController = new Controller(theView);
        //Thread t = new Thread(gen);
        //t.start();
    }
}
