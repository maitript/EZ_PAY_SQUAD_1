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

public class PaymentHistoryDAOTest {
    private Connection connection;
    private PaymentHistoryDAO paymentHistoryDAO;

    @Before
    public void setUp() throws SQLException {
        // Create an in-memory H2 database connection
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        paymentHistoryDAO = new PaymentHistoryDAO(connection);

        // Initialize the database schema
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE payment_history (" +
                    "payment_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT," +
                    "amount DECIMAL(10, 2)," +
                    "payment_date TIMESTAMP," +
                    "description VARCHAR(100))");
        }
    }

    @Test
    public void testAddPaymentHistory() throws SQLException {
        PaymentHistory paymentHistory = new PaymentHistory(1, 1, new BigDecimal("100.00"), new Timestamp(System.currentTimeMillis()), "Service Payment");

        // Insert the payment history
        paymentHistoryDAO.addPaymentHistory(paymentHistory);

        // Retrieve payment history and verify the details
        List<PaymentHistory> paymentHistories = paymentHistoryDAO.getPaymentHistoryByUserId(1);
        assertEquals(1, paymentHistories.size());
        assertEquals(new BigDecimal("100.00"), paymentHistories.get(0).getAmount());
        assertEquals("Service Payment", paymentHistories.get(0).getDescription());
    }

    @After
    public void tearDown() throws SQLException {
        // Drop the table and close the connection
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE payment_history");
        }
        connection.close();
    }
}
