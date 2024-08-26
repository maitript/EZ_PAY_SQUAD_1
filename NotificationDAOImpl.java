package EZPay.UI.noti;

import EZPay.UI.noti.Notification;
import EZPay.UI.noti.User;
import EZPay.UI.noti.NotificationDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl implements NotificationDAO {

    //used to establish a connection
    private Connection getConnection() throws SQLException {
   
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "hr";
        String password = "HR";
        return DriverManager.getConnection(url, user, password);
    }


    //saves a notification in the table
    @Override
    public void save(Notification notification) {
        String sql = "INSERT INTO notifications (payment_id, amount, payment_date, status, sender_id, receiver_id, message) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, notification.getPaymentId());
            stmt.setDouble(2, notification.getAmount());
            stmt.setDate(3, new java.sql.Date(notification.getPaymentDate().getTime()));
            stmt.setString(4, notification.getStatus());
            stmt.setLong(5, notification.getSender().getUserId());
            stmt.setLong(6, notification.getReceiver().getUserId());
            stmt.setString(7, notification.getMessage());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //finds a notification by ID
    @Override
    public Notification findById(Long id) {
        String sql = "SELECT * FROM notifications WHERE payment_id = ?";
        Notification notification = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                notification = new Notification(
                    rs.getLong("payment_id"),
                    rs.getDouble("amount"),
                    rs.getDate("payment_date"),
                    rs.getString("status"),
                    new User(rs.getLong("sender_id"), null, null, null), 
                    new User(rs.getLong("receiver_id"), null, null, null),  
                    rs.getString("message")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notification;
    }

    //Lists all the notifications
    @Override
    public List<Notification> findAll() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Notification notification = new Notification(
                    rs.getLong("payment_id"),
                    rs.getDouble("amount"),
                    rs.getDate("payment_date"),
                    rs.getString("status"),
                    new User(rs.getLong("sender_id"), null, null, null), 
                    new User(rs.getLong("receiver_id"), null, null, null), 
                    rs.getString("message")
                );
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
    
    //This method is unnecessary so haven't called it in the service class
    @Override
    public void update(Notification notification) {
        String sql = "UPDATE notifications SET amount = ?, payment_date = ?, status = ?, sender_id = ?, receiver_id = ?, message = ? WHERE payment_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, notification.getAmount());
            stmt.setDate(2, new java.sql.Date(notification.getPaymentDate().getTime()));
            stmt.setString(3, notification.getStatus());
            stmt.setLong(4, notification.getSender().getUserId());
            stmt.setLong(5, notification.getReceiver().getUserId());
            stmt.setString(6, notification.getMessage());
            stmt.setLong(7, notification.getPaymentId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Deletes a notification by Id
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM notifications WHERE payment_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
