package BusinessLogic;

import javax.swing.*;

public class InvalidInputMessage {
    JFrame frame;
    public InvalidInputMessage(String message) {
        frame = new JFrame();
        JOptionPane.showMessageDialog(frame, message);
    }
}
