package Presentation;

import javax.swing.*;
import java.awt.*;
/**
 * MainView class represents the main menu window.
 * It provides buttons to navigate to client, product, and order management views.
 */
public class MainView extends JFrame{
    public JButton clientButton = new JButton("Manage Clients");
    public JButton productButton = new JButton("Manage Products");
    public JButton orderButton = new JButton("Create Orders");
    /**
     * Constructs the MainView UI.
     * Sets the window title, size, layout, and adds buttons.
     */
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
    /**
     * Main entry point that launches the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new MainController(new MainView());
    }
}
