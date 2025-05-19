package Presentation;

import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    public JButton addButton = new JButton("Add Client");
    public JButton editButton = new JButton("Edit Client");
    public JButton deleteButton = new JButton("Delete Client");
    public JButton backButton = new JButton("Back");
    public JTable clientTable = new JTable();
    public JTextField firstNameField, lastNameField, emailField, phoneField, addressField;
    public ClientView() {
        setTitle("Client Management");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel  buttonPanel= new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        JPanel panel = new JPanel();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);


        add(new JScrollPane(clientTable), "North");
        add(panel,"Center");
        add(buttonPanel, "South");
        setVisible(true);
    }
}
