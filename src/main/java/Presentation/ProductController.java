package Presentation;

import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;

import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductController {
    private ProductView view;
    private ProductBLL products;
    private OrderBLL orders;

    public ProductController(ProductView view) {
        this.view = view;
        products=new ProductBLL();
        orders=new OrderBLL();
        products.populateTable(products.findAll(),view.productTable);
        view.addButton.addActionListener(e -> addProduct());
        view.editButton.addActionListener(e -> editProduct());
        view.deleteButton.addActionListener(e -> deleteProduct());
        view.backButton.addActionListener(e -> view.dispose());
    }

    private void addProduct() {
        String name=view.nameField.getText();
        String description=view.descriptionField.getText();
        String priceStr=view.priceField.getText();
        String stockStr=view.stockField.getText();

        try{
            products.insert(name,description,priceStr,stockStr);
            products.populateTable(products.findAll(), view.productTable);
            view.nameField.setText("");
            view.descriptionField.setText("");
            view.priceField.setText("");
            view.stockField.setText("");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void editProduct() {
        int selectedRow = view.productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Selet a line to update a product!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) view.productTable.getModel();
        try {
            int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            String name = model.getValueAt(selectedRow, 1).toString();
            String description = model.getValueAt(selectedRow, 2).toString();
            int price = Integer.parseInt(model.getValueAt(selectedRow, 3).toString());
            int stock = Integer.parseInt(model.getValueAt(selectedRow, 4).toString());

            Product updated = new Product(id, name, description, price, stock);
            products.update(updated);

            JOptionPane.showMessageDialog(view, "Product has been updated!");
            products.populateTable(products.findAll(), view.productTable);

        }catch(Exception e){
            JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        int selectedRow = view.productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Select a line from the table to delete a product", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = (int) view.productTable.getValueAt(selectedRow, 0);

        List<Order> orderList = orders.findAll().stream()
                .filter(o -> o.getProductId() == id)
                .toList();

        if (!orderList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cannot delete product. There are existing orders.");
            return;
        }
        products.delete(id);
        products.populateTable(products.findAll(), view.productTable);
    }
}
