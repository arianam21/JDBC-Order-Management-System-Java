package Presentation;

import javax.swing.*;
import java.awt.*;

public class ProductView extends JFrame {
    public JButton addButton = new JButton("Add Product");
    public JButton editButton = new JButton("Edit Product");
    public JButton deleteButton = new JButton("Delete Product");
    public JButton backButton = new JButton("Back");
    public JTable productTable = new JTable();
    public JTextField nameField,descriptionField,priceField,stockField;

    public ProductView() {
        setTitle("Product Management");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        JPanel panel = new JPanel();
        nameField = new JTextField();
        descriptionField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();

        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Stock:"));
        panel.add(stockField);


        add(new JScrollPane(productTable), "North");
        add(panel, "Center");
        add(buttonPanel, "South");
        setVisible(true);
    }
}
