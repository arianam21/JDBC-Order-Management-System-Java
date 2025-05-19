package BusinessLogic.validators;
import Model.Client;
import Model.Order;
/**
 * Validator for the Order entity, checking quantity and shipping address.
 */
public class OrderValidator implements Validator<Order> {
    /**
     * Validates the order's quantity and shipping address.
     *
     * @param t the order to validate
     * @throws IllegalArgumentException if quantity is not positive or shipping address is null/empty
     */
    public void validate(Order t) {
        if(t.getQuantity()<=0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if(t.getShippingAddress()==null || t.getShippingAddress().equals("")) {
            throw new IllegalArgumentException("Shipping address cannot be empty");
        }
    }

}
