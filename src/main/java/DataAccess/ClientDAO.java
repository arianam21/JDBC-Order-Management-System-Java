package DataAccess;

import Connection.ConnectionFactory;
import Model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for managing clients in the database.
 */
public class ClientDAO extends AbstractDAO<Client> {
    /**
     * Finds a client by their full name (first and last name).
     *
     * @param fullName the full name of the client (format: "FirstName LastName")
     * @return the Client object if found, otherwise null
     */
    public Client findByFullName(String fullName) {
        String[] parts = fullName.split(" ");
        String firstName = parts[0];
        String lastName = parts[1];
        String query = "SELECT * FROM client WHERE firstName = ? AND lastName = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),rs.getString("phone"),rs.getString("address"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByFullName " + e.getMessage());
        }
        return null;
    }
}
