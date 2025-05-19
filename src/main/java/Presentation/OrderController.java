package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;

import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;


public class OrderController {
    private OrderView view;
    private ClientBLL clients;
    private OrderBLL orders;
    private ProductBLL products;
    public OrderController(OrderView view) {
        this.view = view;
        this.clients = new ClientBLL();
        this.orders = new OrderBLL();
        this.products = new ProductBLL();
        orders.populateTable(orders.findAll(),view.orderTable);
        for(Client c:clients.findAll())
        {
            view.clientBox.addItem(c.getFirstName() + " " + c.getLastName());
        }
        for(Product p:products.findAll())
        {
            view.productBox.addItem(p.getName());
        }
        view.createOrderButton.addActionListener(e -> createOrder());
        view.backButton.addActionListener(e->view.dispose());
    }

    private void createOrder() {
        try {
            String clientName=view.clientBox.getSelectedItem().toString();
            String productName=view.productBox.getSelectedItem().toString();
            int quantity=Integer.parseInt(view.quantityField.getText());
            String address=view.shippingAddressField.getText();

            orders.placeOrder(clientName,productName,quantity,address);
            orders.populateTable(orders.findAll(),view.orderTable);
            JOptionPane.showMessageDialog(view, "Order created");
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid quantity");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: "+ex.getMessage());
        }
    }
}
