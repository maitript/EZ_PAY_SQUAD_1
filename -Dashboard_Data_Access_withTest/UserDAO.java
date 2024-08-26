package myPack;
import java.sql.*;

public class UserDAO {
    private Connection connection;

    // Constructor to initialise connection
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieve a user by their ID
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getBigDecimal("balance")
                );
            }
        }
        return null; // Return null if user is not found
    }

    // Add a new user to the database
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, email, password_hash, balance) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPasswordHash());
            pstmt.setBigDecimal(4, user.getBalance());
            pstmt.executeUpdate(); // Execute the insertion
        }
    }
}

