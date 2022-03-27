import BusinessLogic.InvalidInputMessage;
import BusinessLogic.SimulationManager;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    View theView;
    SimulationManager simulationManager;

    public Controller(View theView) {
        this.theView = theView;
        theView.addStartListener(new addStartListener());
    }

    class addStartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int numberOfClients = -1, numberOfServers = -1, timeLimit = -1, minArrivalTime = -1, maxArrivalTime = -1, minProcessingTime = -1, maxProcessingTime = -1;
            try {
                numberOfClients = Integer.parseInt(theView.nrClientiTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Numarul clientilor are o valoare gresita!");
            }
            try {
                numberOfServers = Integer.parseInt(theView.nrCoziTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Numarul cozilor are o valoare gresita!");
            }
            try {
                timeLimit = Integer.parseInt(theView.intSimulareTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Intervalul simularii are o valoare gresita!");
            }
            try {
                minArrivalTime = Integer.parseInt(theView.timpSosireMinTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Timp sosire(Min) are o valoare gresita!");
            }
            try {
                maxArrivalTime = Integer.parseInt(theView.timpSosireMaxTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Timp sosire(Max) are o valoare gresita!");
            }
            try {
                minProcessingTime = Integer.parseInt(theView.timpServireMinTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Timp servire(Min) are o valoare gresita!");
            }
            try {
                maxProcessingTime = Integer.parseInt(theView.timpServireMaxTF.getText());
            } catch (NumberFormatException nfe) {
                InvalidInputMessage invalidInputMessage = new InvalidInputMessage("Timp servire(Max) are o valoare gresita!");
            }
            if (numberOfClients != -1 && numberOfServers != -1 && timeLimit != -1 && minArrivalTime != -1 && maxArrivalTime != -1 && minProcessingTime != -1 && maxProcessingTime != -1) {
                simulationManager = new SimulationManager(timeLimit, maxProcessingTime, minProcessingTime, maxArrivalTime, minArrivalTime, numberOfServers, numberOfClients, theView);
                Thread t = new Thread(simulationManager);
                t.start();
            }
        }
    }
}
