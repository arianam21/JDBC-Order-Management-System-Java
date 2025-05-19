package Model;
/**
 * Represents a bill associated with an order.
 *
 * @param id the unique identifier of the bill
 * @param orderId the associated order's id
 * @param firstName the first name of the client
 * @param lastName the last name of the client
 * @param productName the name of the product ordered
 * @param quantity the quantity ordered
 * @param price the price per unit of the product
 */
public record Bill(int id,int orderId,String firstName,String lastName,String productName,int quantity,int price) {

}
