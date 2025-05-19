package DataAccess;

import Model.Product;

import java.sql.*;
import java.util.logging.Level;
import Connection.ConnectionFactory;
/**
 * Data Access Object for managing products in the database.
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Finds a product by its name.
     *
     * @param name the name of the product to find
     * @return the Product object if found, otherwise null
     */
    public Product findByName(String name) {
        String query = "SELECT * FROM product WHERE name = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("price"),rs.getInt("price"), rs.getInt("stockQuantity"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findByName " + e.getMessage());
        }
        return null;
    }
}
