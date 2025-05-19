package Model;
/**
 * Represents an order placed by a client for a product.
 * Contains details such as order id, client id, product id, quantity, final price, and shipping address.
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    private int finalPrice;
    private String shippingAddress;
    /**
     * Constructs a new Order with specified attributes.
     *
     * @param id the unique identifier of the order
     * @param clientId the id of the client who placed the order
     * @param productId the id of the ordered product
     * @param quantity the quantity ordered
     * @param finalPrice the final price for the order
     * @param shippingAddress the shipping address for the order
     */
    public Order(int id, int clientId, int productId, int quantity,int finalPrice, String shippingAddress) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.shippingAddress = shippingAddress;
    }
    public Order(){};
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
