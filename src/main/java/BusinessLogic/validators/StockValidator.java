package BusinessLogic.validators;

import Model.Product;
/**
 * Validator to ensure that the stock quantity of a product is positive.
 */
public class StockValidator implements Validator<Product> {
    /**
     * Validates that the stock quantity of the product is greater than zero.
     *
     * @param t the product to validate
     * @throws IllegalArgumentException if the stock quantity is zero or negative
     */
    public void validate(Product t) {
        if (t.getStockQuantity() <= 0) {
            throw new IllegalArgumentException("Stock quantity needs to be a positive number: " + t.getPrice());
        }
    }
}
