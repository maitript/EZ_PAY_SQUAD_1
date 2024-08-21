package dashboardexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DashboardTest {

    private User user1;
    private User user2;
    private Dashboard dashboard;
    private PaymentOverview overview;
    private PaymentHistory history;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        user1 = new User(1L, "Rohit", 500.00);
        user2 = new User(2L, "Rahul", 300.00);
        dashboard = new Dashboard(user1);
        overview = dashboard.getOverview();
        history = dashboard.getHistory();

        // Redirect System.out to capture output for testing
        System.setOut(new PrintStream(outputStream));

        // Adding sample payments
        history.addPayment(new Payment(1L, 100.00, new Date(), "Completed", user1, user2));
        history.addPayment(new Payment(2L, 50.00, new Date(), "Pending", user1, user2));
        history.addPayment(new Payment(3L, 200.00, new Date(), "Completed", user1, user2));
        history.addPayment(new Payment(4L, 120.00, new Date(), "Completed", user2, user1));
    }

    @AfterEach
    public void tearDown() {
        // Reset System.out
        System.setOut(originalOut);
    }

    @Test
    public void testRefreshDashboard() {
        dashboard.refreshDashboard();
        assertEquals(420.00, overview.getTotalAmount(), "Total amount should be 420.00.");
        assertEquals(4, overview.getTotalPayments(), "Total payments should be 4.");
        assertNotNull(overview.getLastPaymentDate(), "Last payment date should not be null.");
    }

    @Test
    public void testDisplayPaymentOverview() {
        dashboard.displayPaymentOverview();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Payment Overview:"), "Output should contain 'Payment Overview:'");
        assertTrue(output.contains("Total Amount: 420.0"), "Output should contain total amount '420.0'");
        assertTrue(output.contains("Total Payments: 4"), "Output should contain total payments '4'");
        // Additional assertions can be made for the last payment date if required
    }

    @Test
    public void testDisplayPaymentHistory() {
        dashboard.displayPaymentHistory();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Payment History:"), "Output should contain 'Payment History:'");
        assertTrue(output.contains("Payment ID: 1, Amount: 100.0"), "Output should contain payment details for ID 1");
        assertTrue(output.contains("Payment ID: 2, Amount: 50.0"), "Output should contain payment details for ID 2");
        assertTrue(output.contains("Payment ID: 3, Amount: 200.0"), "Output should contain payment details for ID 3");
        assertTrue(output.contains("Payment ID: 4, Amount: 120.0"), "Output should contain payment details for ID 4");
    }

    @Test
    public void testCheckBalance() {
        dashboard.checkBalance();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("User Balance: 500.0"), "Output should contain the balance '500.0'");
    }

    @Test
    public void testRefreshDashboardWithNullPayments() {
        history = new PaymentHistory(); // Empty history
        dashboard = new Dashboard(user1);
        dashboard.refreshDashboard();
        PaymentOverview overview = dashboard.getOverview();
        assertEquals(0.00, overview.getTotalAmount(), "Total amount should be 0.00.");
        assertEquals(0, overview.getTotalPayments(), "Total payments should be 0.");
        assertNull(overview.getLastPaymentDate(), "Last payment date should be null.");
    }

    @Test
    public void testDisplayPaymentHistoryWithNullPayments() {
        history = new PaymentHistory(); // Empty history
        dashboard = new Dashboard(user1);
        dashboard.displayPaymentHistory();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Payment History:"), "Output should contain 'Payment History:'");
        assertTrue(output.contains("No payments available."), "Output should indicate no payments available.");
    }

    @Test
    public void testDashboardWithNullUser() {
        dashboard = new Dashboard(null); // Null user
        assertNotNull(dashboard, "Dashboard should be created even with null user.");
        dashboard.displayPaymentOverview(); // This will test if methods handle null user gracefully
        dashboard.checkBalance(); // Should handle null user gracefully
        String output = outputStream.toString().trim();
        assertTrue(output.contains("User Balance: null"), "Output should handle null balance.");
    }

    @Test
    public void testPaymentWithNullValues() {
        Payment paymentWithNulls = new Payment(null, null, null, null, null, null);
        history.addPayment(paymentWithNulls);
        dashboard.refreshDashboard();
        assertEquals(0.00, overview.getTotalAmount(), "Total amount should remain 0.00.");
        assertEquals(5, overview.getTotalPayments(), "Total payments should be 5 with null payment.");
    }
}

