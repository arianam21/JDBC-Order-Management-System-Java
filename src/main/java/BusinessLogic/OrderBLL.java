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
/**
 * Business Logic Layer class for handling Order-related operations.
 */
public class OrderBLL {
    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private BillDAO billDAO;
    private List<Validator<Order>> validators;
    /**
     * Constructs a new OrderBLL instance and initializes DAOs and validators.
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        billDAO = new BillDAO();
        validators = new ArrayList<>();
        validators.add(new OrderValidator());
    }
    /**
     * Retrieves all orders.
     *
     * @return a list of all Order objects.
     */
public List<Order> findAll() {
        return orderDAO.findAll();
}
    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete.
     */
public void delete(int id) {
        orderDAO.delete(id);
}
    /**
     * Finds an order by its ID.
     *
     * @param id the order ID to search for.
     * @return the found Order.
     */
public Order findById(int id) {
        return orderDAO.findById(id);
}
    /**
     * Places an order for a client with a product and quantity.
     * Validates stock availability, updates stock, inserts order and creates a bill.
     *
     * @param clientName  the full name of the client placing the order.
     * @param productName the name of the product to order.
     * @param quantity    the quantity to order.
     * @param address     the delivery address.
     * @throws IllegalArgumentException if client or product is not found or insufficient stock.
     * @throws IllegalStateException    if the order could not be inserted.
     */
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
    /**
     * Populates the given JTable with a list of orders.
     *
     * @param orders the list of orders to display.
     * @param table  the JTable to populate.
     */
public void populateTable(List<Order>orders,JTable table)
{
    orderDAO.populateTable(orders,table);
}
    /**
     * Deletes orders by the client ID.
     *
     * @param id the client ID whose orders should be deleted.
     */
public void deleteByClientId(int id)
{
    orderDAO.deleteByClientId(id);
}


}
