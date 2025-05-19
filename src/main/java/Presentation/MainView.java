package Presentation;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame{
    public JButton clientButton = new JButton("Manage Clients");
    public JButton productButton = new JButton("Manage Products");
    public JButton orderButton = new JButton("Create Orders");

    public MainView() {
        setTitle("Main Menu");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));
        panel.add(clientButton);
        panel.add(productButton);
        panel.add(orderButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainController(new MainView());
    }
}
