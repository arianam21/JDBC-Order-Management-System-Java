package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory class to manage database connections.
 * Provides methods to create and close database connections, statements, and result sets.
 */
public class ConnectionFactory {

    private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String DBURL ="jdbc:mysql://localhost:3306/applicationdb";
    private static final String USER ="root";
    private static final String PASS ="root";

    private static ConnectionFactory singleInstance=new ConnectionFactory();
    /**
     * Loads the database driver class.
     */
    public ConnectionFactory() {
        try{
            Class.forName(DRIVER);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Creates and returns a new database connection.
     *
     * @return a new Connection to the database, or null if connection fails.
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Returns a new connection from the single instance of this factory.
     *
     * @return a new Connection.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
    /**
     * Closes the given database connection.
     *
     * @param connection the Connection to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    /**
     * Closes the given SQL statement.
     *
     * @param statement the Statement to be closed.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    /**
     * Closes the given result set.
     *
     * @param resultSet the ResultSet to be closed.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }


}
