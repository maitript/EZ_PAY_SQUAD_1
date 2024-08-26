package myPack;
import java.sql.SQLException;
import java.util.List;

public class Dashboard {
    private PaymentOverviewDAO paymentOverviewDAO;
    private PaymentHistoryDAO paymentHistoryDAO;
    private UserDAO userDAO;
    private User user;

    public Dashboard(User user, PaymentOverviewDAO paymentOverviewDAO, PaymentHistoryDAO paymentHistoryDAO, UserDAO userDAO) {
        this.user = user;
        this.paymentOverviewDAO = paymentOverviewDAO;
        this.paymentHistoryDAO = paymentHistoryDAO;
        this.userDAO = userDAO;
    }

    public void refreshDashboard() throws SQLException {
        List<PaymentHistory> payments = paymentHistoryDAO.getPaymentHistoryByUserId(user.getUserId());
        PaymentOverview overview = paymentOverviewDAO.getPaymentOverviewByUserId(user.getUserId());
        user.setBalance(overview.getTotalPaid().subtract(overview.getTotalReceived())); // Example of updating user balance
    }

    public PaymentOverview getOverview() throws SQLException {
        return paymentOverviewDAO.getPaymentOverviewByUserId(user.getUserId());
    }

    public List<PaymentHistory> getHistory() throws SQLException {
        return paymentHistoryDAO.getPaymentHistoryByUserId(user.getUserId());
    }

    public void displayPaymentProgress() {
        System.out.println("Displaying payment progress...");
    }

    public void displayPaymentOverview() {
        try {
            PaymentOverview overview = getOverview();
            System.out.println("Payment Overview:");
            System.out.println("Total Amount: " + overview.getTotalPaid());
            System.out.println("Total Payments: " + overview.getTotalReceived());
            System.out.println("Last Payment Date: " + overview.getLastPaymentDate());
        } catch (SQLException e) {
            System.err.println("Error retrieving payment overview: " + e.getMessage());
        }
    }

    public void displayPaymentHistory() {
        try {
            List<PaymentHistory> history = getHistory();
            System.out.println("Payment History:");
            history.forEach(payment -> {
                System.out.println("Payment ID: " + payment.getPaymentId() + ", Amount: " + payment.getAmount() +
                        ", Date: " + payment.getPaymentDate() + ", Description: " + payment.getDescription());
            });
        } catch (SQLException e) {
            System.err.println("Error retrieving payment history: " + e.getMessage());
        }
    }

    public void checkBalance() {
        System.out.println("User Balance: " + user.getBalance());
    }
}
