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


public class ClientBLL {
    private List<Validator<Client>> validators;
    ClientDAO clientDAO;
    public ClientBLL() {
        clientDAO=new ClientDAO();
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
    }
    public Client findById(int id) {
        Client client = clientDAO.findById(id);
        if(client == null)
            throw new NoSuchElementException("Client with id " + id + " not found");
    return client;
    }
    public Client findByFullName(String fullName) {
        Client client = clientDAO.findByFullName(fullName);
        if(client == null)
            throw new NoSuchElementException("Client with name " + fullName + " not found");
        return client;
    }
    public List<Client> findAll() {
        return clientDAO.findAll();
    }
    public Client insert(String firstName, String lastName, String email, String phone,String address) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                phone.isEmpty() || address.isEmpty()) {
            throw new NullPointerException("Lines must not be empty");
        }
        Client c=new Client(0,firstName,lastName,email,phone,address);
        validators.forEach(v -> v.validate(c));
        return clientDAO.insert(c);
    }
    public Client update(Client client) {
        validators.forEach(v -> v.validate(client));
        return clientDAO.update(client);
    }
    public void delete(int id) {
        clientDAO.delete(id);
    }

    public void populateTable(List<Client> clients, JTable clientTable) {
        clientDAO.populateTable(clients, clientTable);
    }
}
