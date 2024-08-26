package myPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentOverviewDAO {
    private Connection connection;

    public PaymentOverviewDAO(Connection connection) {
        this.connection = connection;
    }

    // Example method to get payment overview
    public List<PaymentOverview> getPaymentOverviewByUserId(int userId) throws SQLException {
        List<PaymentOverview> overviews = new ArrayList<>();
        String sql = "SELECT * FROM payment_overview WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                overviews.add(new PaymentOverview(
                        rs.getInt("overview_id"),
                        rs.getInt("user_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("summary")
                ));
            }
        }
        return overviews;
    }
}
