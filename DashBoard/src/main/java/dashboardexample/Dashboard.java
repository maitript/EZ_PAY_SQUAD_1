package dashboardexample;


import java.util.Date;
import java.util.List;

public class Dashboard {
    private PaymentOverview overview;
    private PaymentHistory history;
    private User user;

    public Dashboard(User user) {
        this.overview = new PaymentOverview();
        this.history = new PaymentHistory();
        this.user = user;
    }

    public void refreshDashboard() {
        overview.updateOverview(history.getPayments());
    }

    public PaymentOverview getOverview() {
        return overview;
    }

    public PaymentHistory getHistory() {
        return history;
    }

    public void displayPaymentProgress() {
        System.out.println("Displaying payment progress...");
    }

    public void displayPaymentOverview() {
        refreshDashboard();
        System.out.println("Payment Overview:");
        System.out.println("Total Amount: " + overview.getTotalAmount());
        System.out.println("Total Payments: " + overview.getTotalPayments());
        System.out.println("Last Payment Date: " + overview.getLastPaymentDate());
    }

    public void displayPaymentHistory() {
        System.out.println("Payment History:");
        history.getPayments().forEach(payment -> {
            System.out.println("Payment ID: " + payment.getPaymentId() + ", Amount: " + payment.getAmount() +
                    ", Date: " + payment.getPaymentDate() + ", Status: " + payment.getStatus() +
                    ", Sender: " + payment.getSender().getUserName() + ", Receiver: " + payment.getReceiver().getUserName());
        });
    }

    public void checkBalance() {
        System.out.println("User Balance: " + user.getBalance());
    }
}
