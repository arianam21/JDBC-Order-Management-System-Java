package Presentation;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {
    public JComboBox<String> clientBox = new JComboBox<>();
    public JComboBox<String> productBox = new JComboBox<>();
    public JTextField quantityField = new JTextField();
    public JTextField shippingAddressField = new JTextField();
    public JButton createOrderButton = new JButton("Create Order");
    public JButton backButton = new JButton("Back");
    public JTable orderTable;
    public JScrollPane orderScrollPane;

    public OrderView() {
        setTitle("Order Creation");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15,1));
        panel.add(new JLabel("Client:"));
        panel.add(clientBox);
        panel.add(new JLabel("Product:"));
        panel.add(productBox);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Shipping Address:"));
        panel.add(shippingAddressField);
        panel.add(createOrderButton);
        panel.add(backButton);

        orderTable=new JTable();
        orderScrollPane=new JScrollPane(orderTable);

        add(panel, "Center");
        add(orderScrollPane,"South");
        setVisible(true);
    }

}
