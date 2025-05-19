package BusinessLogic.validators;
import Model.Client;
import Model.Order;
public class OrderValidator implements Validator<Order> {
    public void validate(Order t) {
        if(t.getQuantity()<=0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if(t.getShippingAddress()==null || t.getShippingAddress().equals("")) {
            throw new IllegalArgumentException("Shipping address cannot be empty");
        }
    }

}
