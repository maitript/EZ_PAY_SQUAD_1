package myPack;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection connection;

    // Constructor to initialize connection
    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new payment record to the database
    public void addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO transactions (from_account_id, to_account_id, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getFromAccountId());
            pstmt.setInt(2, payment.getToAccountId());
            pstmt.setBigDecimal(3, payment.getAmount());
            pstmt.setTimestamp(4, payment.getTimestamp());
            pstmt.executeUpdate(); // Execute the insertion
        }
    }

    // Retrieve payments associated with a specific account ID
    public List<Payment> getPaymentsByAccountId(int accountId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE from_account_id = ? OR to_account_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, accountId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("from_account_id"),
                    rs.getInt("to_account_id"),
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("timestamp")
                ));
            }
        }
        return payments; // Return the list of payments
    }
}
