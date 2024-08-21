package dashboardexample;


import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Creating users
        User user1 = new User(1L, "Rohit", 500.00);
        User user2 = new User(2L, "Rahul", 300.00);

        // Creating a dashboard for the sender
        Dashboard dashboard = new Dashboard(user1);

        // Adding some sample payments
        dashboard.getHistory().addPayment(new Payment(1L, 100.00, new Date(), "Completed", user1, user2));
        dashboard.getHistory().addPayment(new Payment(2L, 50.00, new Date(), "Pending", user1, user2));
        dashboard.getHistory().addPayment(new Payment(3L, 200.00, new Date(), "Completed", user1, user2));
        dashboard.getHistory().addPayment(new Payment(3L, 120.00, new Date(), "Completed", user2, user1));

        // Display the overview
        dashboard.displayPaymentOverview();

        // Display payment history
        dashboard.displayPaymentHistory();

        // Display payment progress (placeholder)
        dashboard.displayPaymentProgress();

        // Check balance for the sender
        dashboard.checkBalance();

        // Check balance for the receiver (only for testing)
        //System.out.println("Receiver Balance: " + receiver.getBalance());
    }
}
