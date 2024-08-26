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
import java.sql.Timestamp;
import java.util.List;

public class PaymentDAOTest {
    private Connection connection;
    private PaymentDAO paymentDAO;

    @Before
    public void setUp() throws SQLException {
        // Create an in-memory H2 database connection
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        paymentDAO = new PaymentDAO(connection);

        // Initialize the database schema
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE transactions (" +
                    "payment_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "from_account_id INT," +
                    "to_account_id INT," +
                    "amount DECIMAL(10, 2)," +
                    "timestamp TIMESTAMP)");
        }
    }

    @Test
    public void testAddPayment() throws SQLException {
        Payment payment = new Payment(1, 1, 2, new BigDecimal("100.00"), new Timestamp(System.currentTimeMillis()));

        // Insert the payment
        paymentDAO.addPayment(payment);

        // Retrieve payments and verify the details
        List<Payment> payments = paymentDAO.getPaymentsByAccountId(1);
        assertEquals(1, payments.size());
        assertEquals(new BigDecimal("100.00"), payments.get(0).getAmount());
        assertEquals(2, payments.get(0).getToAccountId());
    }

    @After
    public void tearDown() throws SQLException {
        // Drop the table and close the connection
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE transactions");
        }
        connection.close();
    }
}
