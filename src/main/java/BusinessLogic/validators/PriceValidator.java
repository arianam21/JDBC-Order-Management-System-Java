package BusinessLogic.validators;


import Model.Product;


import java.util.regex.Pattern;

public class PriceValidator implements Validator<Product> {
    public void validate(Product t) {
        if (t.getPrice() <= 0) {
            throw new IllegalArgumentException("Price needs to be a positive number: " + t.getPrice());
        }
    }

}