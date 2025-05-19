package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.List;
/**
 * ClientController manages interaction between ClientView and ClientBLL.
 * Handles UI events to add, edit, and delete clients.
 */
public class ClientController {
    private ClientView view;
    private ClientBLL clients;
    private OrderBLL orders;
    /**
     * Constructs ClientController with the provided ClientView.
     * Populates the client table and attaches action listeners.
     *
     * @param view the ClientView instance to control
     */
    public ClientController(ClientView view) {
        this.view = view;
        this.clients = new ClientBLL();
        orders = new OrderBLL();
        /*List<Client> clientList=clients.findAll();
        clientList.sort(Comparator.comparing(Client::getFirstName));
        clients.populateTable(clientList,view.clientTable);*/
        clients.populateTable(clients.findAll(),view.clientTable);
        view.addButton.addActionListener(e -> addClient());
        view.editButton.addActionListener(e -> editClient());
        view.deleteButton.addActionListener(e -> deleteClient());
        view.backButton.addActionListener(e -> view.dispose());
    }
    /**
     * Adds a new client using input from the view's text fields.
     * Clears the input fields after insertion.
     * Shows error message dialog on failure.
     */
    private void addClient() {
        String firstName=view.firstNameField.getText();
        String lastName=view.lastNameField.getText();
        String email=view.emailField.getText();
        String phone=view.phoneField.getText();
        String address=view.addressField.getText();

        try
        {
            clients.insert(firstName,lastName,email,phone,address);
            clients.populateTable(clients.findAll(), view.clientTable);
            view.firstNameField.setText("");
            view.lastNameField.setText("");
            view.emailField.setText("");
            view.phoneField.setText("");
            view.addressField.setText("");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    /**
     * Edits the selected client based on the current table row values.
     * Shows an error message if no row is selected or if update fails.
     */
    private void editClient() {
        int selectedRow = view.clientTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Select a line from the table to update client");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) view.clientTable.getModel();
        try {
            int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            String firstName = model.getValueAt(selectedRow, 1).toString();
            String lastName = model.getValueAt(selectedRow, 2).toString();
            String email = model.getValueAt(selectedRow, 3).toString();
            String phone = model.getValueAt(selectedRow, 4).toString();
            String address = model.getValueAt(selectedRow, 5).toString();

            Client updatedClient = new Client(id,firstName,lastName,email,phone,address);
            clients.update(updatedClient);

            JOptionPane.showMessageDialog(view, "The client has been updated");
            clients.populateTable(clients.findAll(), view.clientTable);

        } catch(Exception e){
            JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

    }
    /**
     * Deletes the selected client after confirming a row is selected.
     * Also deletes related orders by client ID.
     * Refreshes the client table after deletion.
     */
    private void deleteClient() {
        int selectedRow = view.clientTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Select a line from the table to delete client");
            return;
        }
        int id = (int) view.clientTable.getValueAt(selectedRow, 0);
        orders.deleteByClientId(id);
        clients.delete(id);
        clients.populateTable(clients.findAll(), view.clientTable);
    }

}
