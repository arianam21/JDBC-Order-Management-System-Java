package BusinessLogic;

import BusinessLogic.validators.PriceValidator;
import BusinessLogic.validators.StockValidator;
import Model.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import BusinessLogic.validators.Validator;
import DataAccess.ProductDAO;

import javax.swing.*;
/**
 * Business Logic Layer class for handling Product-related operations.
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    ProductDAO productDAO;
    /**
     * Constructs a new ProductBLL instance, initializes the validators and DAO.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
        validators = new ArrayList<>();
        validators.add(new StockValidator());
        validators.add(new PriceValidator());
    }
    /**
     * Finds a product by its ID.
     *
     * @param id the product ID to search for.
     * @return the found Product.
     * @throws NoSuchElementException if no product is found with the given ID.
     */
    public Product findById(int id) {
        Product product = productDAO.findById(id);
        if(product == null)
            throw new NoSuchElementException("Product with id " + id + " not found");
        return product;
    }
    /**
     * Finds a product by its name.
     *
     * @param name the name of the product to search for.
     * @return the found Product.
     * @throws NoSuchElementException if no product is found with the given name.
     */
    public Product findByName(String name) {
        Product product = productDAO.findByName(name);
        if(product == null)
            throw new NoSuchElementException("Product with name " + name + " not found");
        return product;
    }
    /**
     * Retrieves all products.
     *
     * @return a list of all Product objects.
     */
    public List<Product> findAll() {
        return productDAO.findAll();
    }
    /**
     * Inserts a new product after validating the input fields.
     *
     * @param name        the product name.
     * @param description the product description.
     * @param priceStr    the price as a string (must be parsable to int).
     * @param stockStr    the stock quantity as a string (must be parsable to int).
     * @return the inserted Product.
     * @throws NullPointerException      if any input is empty.
     * @throws NumberFormatException     if price or stock cannot be parsed to integer.
     * @throws IllegalArgumentException  if validation fails.
     */
    public Product insert(String name, String description, String priceStr, String stockStr) {

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() ||
                stockStr.isEmpty()) {
            throw new NullPointerException("Lines must not be empty");
        }
        int price,stock;
        try{
            price=Integer.parseInt(priceStr);
            stock=Integer.parseInt(stockStr);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException("Price and stock must be integers");
        }
        Product p=new Product(0,name,description,price,stock);
        for(Validator<Product> v : validators) {
            v.validate(p);
        }
        return productDAO.insert(p);
    }
    /**
     * Updates an existing product after validation.
     *
     * @param product the Product to update.
     * @return the updated Product.
     * @throws IllegalArgumentException if validation fails.
     */
    public Product update(Product product) {
        validators.forEach(v -> v.validate(product));
        return productDAO.update(product);
    }
    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID to delete.
     */
    public void delete(int id) {
        productDAO.delete(id);
    }
    /**
     * Populates the given JTable with the provided list of products.
     *
     * @param products     the list of products to display.
     * @param productTable the JTable to populate.
     */
    public void populateTable(List<Product> products, JTable productTable) {
        productDAO.populateTable(products, productTable);
    }
}
