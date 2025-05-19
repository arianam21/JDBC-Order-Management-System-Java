package DataAccess;

import Model.Product;

import java.sql.*;
import java.util.logging.Level;
import Connection.ConnectionFactory;

public class ProductDAO extends AbstractDAO<Product> {
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
