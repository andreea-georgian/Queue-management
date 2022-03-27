package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    JFrame frame = new JFrame("Queue management");
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel11 = new JPanel();
    JPanel panel12 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JLabel nrClientiLabel = new JLabel("Numarul clientilor: ");
    public JTextField nrClientiTF = new JTextField();
    JLabel nrCoziLabel = new JLabel("Numarul cozilor: ");
    public JTextField nrCoziTF = new JTextField();
    JLabel intSimulareLabel = new JLabel("Intervalul simularii: ");
    public JTextField intSimulareTF = new JTextField();
    JLabel timpSosireMinLabel = new JLabel("Timp sosire (Min): ");
    public JTextField timpSosireMinTF = new JTextField();
    JLabel timpSosireMaxLabel = new JLabel("Timp sosire (Max): ");
    public JTextField timpSosireMaxTF = new JTextField();
    JLabel timpServireMinLabel = new JLabel("Timp de servire (Min): ");
    public JTextField timpServireMinTF = new JTextField();
    JLabel timpServireMaxLabel = new JLabel("Timp de servire (Max): ");
    public JTextField timpServireMaxTF = new JTextField();
    JButton start = new JButton("Start");
    public JTextArea cozi = new JTextArea();
    public JTextArea asteptare = new JTextArea();

    public View() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        panel.setLayout(new GridLayout(1, 3));
        panel1.setLayout(new GridLayout(1, 2));
        panel11.setLayout(new GridLayout(8, 1));
        panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));
        panel2.setLayout(new GridLayout(1, 1));
        panel3.setLayout(new GridLayout(1, 1));

        panel11.add(nrClientiLabel);
        panel11.add(nrCoziLabel);
        panel11.add(intSimulareLabel);
        panel11.add(timpSosireMinLabel);
        panel11.add(timpSosireMaxLabel);
        panel11.add(timpServireMinLabel);
        panel11.add(timpServireMaxLabel);
        panel11.add(start);

        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(nrClientiTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(nrCoziTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(intSimulareTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(timpSosireMinTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(timpSosireMaxTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(timpServireMinTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 5)));
        panel12.add(timpServireMaxTF);
        panel12.add(Box.createRigidArea(new Dimension(0, 45)));

        cozi.setBackground(new Color(166, 238, 238, 224));
        asteptare.setBackground(new Color(234, 255, 255, 224));

        panel2.add(cozi);
        panel3.add(asteptare);

        panel11.setBackground(new Color(255, 155, 155, 219));
        panel12.setBackground(new Color(255, 155, 155, 219));

        panel1.add(panel11);
        panel1.add(panel12);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        frame.add(panel);
        frame.setLocation(370, 200);
        frame.setVisible(true);
    }

    public void addStartListener(ActionListener startListener) {start.addActionListener(startListener);}
}
