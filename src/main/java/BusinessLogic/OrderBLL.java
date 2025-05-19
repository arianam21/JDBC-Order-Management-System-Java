package BusinessLogic;
import BusinessLogic.validators.OrderValidator;
import BusinessLogic.validators.Validator;
import DataAccess.BillDAO;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import DataAccess.OrderDAO;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private BillDAO billDAO;
    private List<Validator<Order>> validators;
    public OrderBLL() {
        orderDAO = new OrderDAO();
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        billDAO = new BillDAO();
        validators = new ArrayList<>();
        validators.add(new OrderValidator());
    }
public List<Order> findAll() {
        return orderDAO.findAll();
}
public void delete(int id) {
        orderDAO.delete(id);
}
public Order findById(int id) {
        return orderDAO.findById(id);
}
public void placeOrder(String clientName,String productName,int quantity,String address) {

    Client client=clientDAO.findByFullName(clientName);
    if (client==null) {
        throw new IllegalArgumentException( "Client not found");
    }
    Product product=productDAO.findByName(productName);
    if (product==null) {
        throw new IllegalArgumentException( "Product not found");
    }
    if (product.getStockQuantity()<quantity) {
        throw new IllegalArgumentException("Not enough stock");
    }
    int total = product.getPrice() * quantity;

    Order newOrder=new Order(0,client.getId(),product.getId(),quantity,total,address);
    validators.forEach(v -> v.validate(newOrder));
    int orderId=orderDAO.addOrder(newOrder);
    if (orderId == -1) {
        throw new IllegalStateException("Could not insert order");
    }
    product.setStockQuantity(product.getStockQuantity() - quantity);
    productDAO.update(product);
    Bill bill=new Bill(0, orderId,client.getFirstName(),client.getLastName(),product.getName(),quantity,product.getPrice());
    billDAO.insertBill(bill);
}
public void populateTable(List<Order>orders,JTable table)
{
    orderDAO.populateTable(orders,table);
}
public void deleteByClientId(int id)
{
    orderDAO.deleteByClientId(id);
}


}
