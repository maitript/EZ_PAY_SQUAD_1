package dashboardexample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class PaymentHistory {
    private List<Payment> payments;

    public PaymentHistory() {
        this.payments = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        // Update balances if the payment is completed
        if ("Completed".equals(payment.getStatus())) {
            User sender = payment.getSender();
            User receiver = payment.getReceiver();

            // Deduct amount from sender's balance
            sender.setBalance(sender.getBalance() - payment.getAmount());

            // Add amount to receiver's balance
            receiver.setBalance(receiver.getBalance() + payment.getAmount());
        }

        payments.add(payment);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Payment> getPaymentsByDateRange(Date start, Date end) {
        return payments.stream()
                .filter(payment -> !payment.getPaymentDate().before(start) && !payment.getPaymentDate().after(end))
                .collect(Collectors.toList());
    }
}
