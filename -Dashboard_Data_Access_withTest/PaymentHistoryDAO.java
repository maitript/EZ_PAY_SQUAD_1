package myPack;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryDAO {
    private Connection connection;

    // Constructor to initialize connection
    public PaymentHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new payment history record to the database
    public void addPaymentHistory(PaymentHistory paymentHistory) throws SQLException {
        String sql = "INSERT INTO payment_history (user_id, amount, payment_date, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, paymentHistory.getUserId());
            pstmt.setBigDecimal(2, paymentHistory.getAmount());
            pstmt.setTimestamp(3, paymentHistory.getPaymentDate());
            pstmt.setString(4, paymentHistory.getDescription());
            pstmt.executeUpdate(); // Execute the insertion
        }
    }

    // Retrieve payment history for a specific user ID
    public List<PaymentHistory> getPaymentHistoryByUserId(int userId) throws SQLException {
        List<PaymentHistory> paymentHistories = new ArrayList<>();
        String sql = "SELECT * FROM payment_history WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                paymentHistories.add(new PaymentHistory(
                    rs.getInt("payment_id"),
                    rs.getInt("user_id"),
                    rs.getBigDecimal("amount"),
                    rs.getTimestamp("payment_date"),
                    rs.getString("description")
                ));
            }
        }
        return paymentHistories; // Return the list of payment histories
    }
}

