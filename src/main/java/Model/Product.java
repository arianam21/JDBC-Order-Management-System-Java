package Model;
/**
 * Represents a product in the system.
 * Contains details such as id, name, description, price, and stock quantity.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int stockQuantity;
    /**
     * Constructs a new Product with specified attributes.
     *
     * @param id the unique identifier of the product
     * @param name the name of the product
     * @param description the description of the product
     * @param price the price of the product
     * @param stockQuantity the available stock quantity of the product
     */
    public Product(int id, String name, String description, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;

    }
    public Product() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description +", price=" + price + ", stockQuantity=" + stockQuantity
                + "]";
    }
}
