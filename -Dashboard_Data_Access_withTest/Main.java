package myPack;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish the database connection
            connection = DBConnection.getConnection();
            
            // Initialize DAOs with the database connection
            UserDAO userDAO = new UserDAO(connection);
            PaymentHistoryDAO paymentHistoryDAO = new PaymentHistoryDAO(connection);
            PaymentOverviewDAO paymentOverviewDAO = new PaymentOverviewDAO(connection);
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            
            // Create a sample user (for demonstration purposes)
            User user = new User(1, "JohnDoe", "john@example.com", "hashedpassword", BigDecimal.ZERO);
            
            // Initialise Dashboard
            Dashboard dashboard = new Dashboard(user, paymentOverviewDAO, paymentHistoryDAO, userDAO);
            
            // Display information
            dashboard.refreshDashboard(); // Update the dashboard with fresh data
            dashboard.displayPaymentProgress(); // Display payment progress (placeholder)
            dashboard.displayPaymentOverview(); // Show payment overview
            dashboard.displayPaymentHistory(); // Show payment history
            dashboard.checkBalance(); // Show user balance
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            // Ensure the connection is closed
            DBConnection.closeConnection(connection);
        }
    }
}
