package BusinessLogic.validators;


import Model.Product;


import java.util.regex.Pattern;
/**
 * Validator to ensure that the price of a product is positive.
 */
public class PriceValidator implements Validator<Product> {
    /**
     * Validates that the price of the product is greater than zero.
     *
     * @param t the product to validate
     * @throws IllegalArgumentException if the price is zero or negative
     */
    public void validate(Product t) {
        if (t.getPrice() <= 0) {
            throw new IllegalArgumentException("Price needs to be a positive number: " + t.getPrice());
        }
    }

}