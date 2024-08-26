package myPack;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOTest {
    private Connection connection;
    private UserDAO userDAO;

    @Before
    public void setUp() throws SQLException {
        // Create an in-memory H2 database connection
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        userDAO = new UserDAO(connection);

        // Initialize the database schema
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50)," +
                    "email VARCHAR(50)," +
                    "password_hash VARCHAR(100)," +
                    "balance DECIMAL(10, 2))");
        }
    }

    @Test
    public void testAddUser() throws SQLException {
        User user = new User(1, "JohnDoe", "john@example.com", "hashedpassword", BigDecimal.ZERO);

        // Insert the user
        userDAO.addUser(user);

        // Retrieve the user and verify the details
        User retrievedUser = userDAO.getUserById(1);
        assertNotNull(retrievedUser);
        assertEquals("JohnDoe", retrievedUser.getUsername());
        assertEquals("john@example.com", retrievedUser.getEmail());
        assertEquals("hashedpassword", retrievedUser.getPasswordHash());
        assertEquals(BigDecimal.ZERO, retrievedUser.getBalance());
    }

    @Test
    public void testGetUserById_UserNotExists() throws SQLException {
        User user = userDAO.getUserById(999);
        assertNull(user);
    }

    @After
    public void tearDown() throws SQLException {
        // Drop the table and close the connection
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE users");
        }
        connection.close();
    }
}
