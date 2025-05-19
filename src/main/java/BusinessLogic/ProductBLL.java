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

public class ProductBLL {
    private List<Validator<Product>> validators;
    ProductDAO productDAO;
    public ProductBLL() {
        productDAO = new ProductDAO();
        validators = new ArrayList<>();
        validators.add(new StockValidator());
        validators.add(new PriceValidator());
    }
    public Product findById(int id) {
        Product product = productDAO.findById(id);
        if(product == null)
            throw new NoSuchElementException("Product with id " + id + " not found");
        return product;
    }
    public Product findByName(String name) {
        Product product = productDAO.findByName(name);
        if(product == null)
            throw new NoSuchElementException("Product with name " + name + " not found");
        return product;
    }
    public List<Product> findAll() {
        return productDAO.findAll();
    }
    public Product insert(String name, String description, String priceStr, String stockStr) {

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() ||
                stockStr.isEmpty()) {
            throw new NullPointerException("Lines must not be empty");
        }
        int price,stock;
        try{
            price=Integer.parseInt(stockStr);
            stock=Integer.parseInt(stockStr);
        }
        catch(NumberFormatException e) {
            throw new NullPointerException("Price and stock must be integers");
        }
        Product p=new Product(0,name,description,price,stock);
        for(Validator<Product> v : validators) {
            v.validate(p);
        }
        return productDAO.insert(p);
    }
    public Product update(Product product) {
        validators.forEach(v -> v.validate(product));
        return productDAO.update(product);
    }
    public void delete(int id) {
        productDAO.delete(id);
    }

    public void populateTable(List<Product> products, JTable productTable) {
        productDAO.populateTable(products, productTable);
    }
}
