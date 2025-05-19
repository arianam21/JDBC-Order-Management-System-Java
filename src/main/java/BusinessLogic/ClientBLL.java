package BusinessLogic;

import BusinessLogic.validators.PhoneValidator;
import Model.Client;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import BusinessLogic.validators.EmailValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ClientDAO;

import javax.swing.*;

/**
 * Business Logic Layer class for handling Client-related operations.
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    ClientDAO clientDAO;
    /**
     * Constructs a new ClientBLL instance, initializes the validators and DAO.
     */
    public ClientBLL() {
        clientDAO=new ClientDAO();
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
    }
    /**
     * Finds a client by their ID.
     *
     * @param id the client ID to search for.
     * @return the found Client.
     * @throws NoSuchElementException if no client is found with the given ID.
     */
    public Client findById(int id) {
        Client client = clientDAO.findById(id);
        if(client == null)
            throw new NoSuchElementException("Client with id " + id + " not found");
    return client;
    }
    /**
     * Finds a client by their full name.
     *
     * @param fullName the full name of the client to search for.
     * @return the found Client.
     * @throws NoSuchElementException if no client is found with the given name.
     */
    public Client findByFullName(String fullName) {
        Client client = clientDAO.findByFullName(fullName);
        if(client == null)
            throw new NoSuchElementException("Client with name " + fullName + " not found");
        return client;
    }
    /**
     * Retrieves all clients.
     *
     * @return a list of all {@link Client} objects.
     */
    public List<Client> findAll() {
        return clientDAO.findAll();
    }
    /**
     * Inserts a new client after validating the input fields.
     *
     * @param firstName the client's first name.
     * @param lastName  the client's last name.
     * @param email     the client's email.
     * @param phone     the client's phone number.
     * @param address   the client's address.
     * @return the inserted {@link Client}.
     * @throws NullPointerException     if any input is empty.
     * @throws IllegalArgumentException if validation fails.
     */
    public Client insert(String firstName, String lastName, String email, String phone,String address) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                phone.isEmpty() || address.isEmpty()) {
            throw new NullPointerException("Lines must not be empty");
        }
        Client c=new Client(0,firstName,lastName,email,phone,address);
        validators.forEach(v -> v.validate(c));
        return clientDAO.insert(c);
    }
    /**
     * Updates an existing client after validation.
     *
     * @param client the Client to update.
     * @return the updated Client.
     * @throws IllegalArgumentException if validation fails.
     */
    public Client update(Client client) {
        validators.forEach(v -> v.validate(client));
        return clientDAO.update(client);
    }
    /**
     * Deletes a client by their ID.
     *
     * @param id the client ID to delete.
     */
    public void delete(int id) {
        clientDAO.delete(id);
    }
    /**
     * Populates the given JTable with a list of clients.
     *
     * @param clients     the list of clients to display.
     * @param clientTable the JTable to populate.
     */
    public void populateTable(List<Client> clients, JTable clientTable) {
        clientDAO.populateTable(clients, clientTable);
    }
}
