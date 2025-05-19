package DataAccess;

import Connection.ConnectionFactory;
import Model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.logging.Level;


public class BillDAO extends AbstractDAO<Bill> {

    public void insertBill(Bill bill) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO Log(orderId, firstName, lastName, productName, quantity, price) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.firstName());
            statement.setString(3, bill.lastName());
            statement.setString(4, bill.productName());
            statement.setInt(5, bill.quantity());
            statement.setInt(6, bill.price());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

}

