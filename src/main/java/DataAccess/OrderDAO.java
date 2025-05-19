package DataAccess;

import Model.Order;

import java.sql.*;
import java.util.logging.Level;

import Connection.ConnectionFactory;
/**
 * Data Access Object for managing orders in the database.
 */
public class OrderDAO extends AbstractDAO<Order> {
    /**
     * Adds a new order to the database.
     *
     * @param order the Order object to add
     * @return the generated id of the inserted order, or 0 if insertion failed
     */
    public int addOrder(Order order) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet = null;
        String insertOrderQuery="INSERT INTO `Order`(clientId, productId, quantity,finalPrice, shippingAddress) VALUES(?,?,?,?,?)";
        try{
            connection= ConnectionFactory.getConnection();
            statement= connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getFinalPrice());
            statement.setString(5, order.getShippingAddress());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch(SQLException ex){
            LOGGER.log(Level.WARNING,"OrderDAO:addOrder"+ ex.getMessage());
        }
        finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
    /**
     * Deletes orders from the database by client ID.
     *
     * @param clientId the client ID whose orders should be deleted
     */
    public void deleteByClientId(int clientId) {
        String query = "DELETE FROM `Order` WHERE clientId = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, clientId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:deleteByClientId " + e.getMessage());
        }
    }
}
