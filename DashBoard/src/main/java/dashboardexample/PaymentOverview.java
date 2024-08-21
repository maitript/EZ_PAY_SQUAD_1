package dashboardexample;

import java.util.Date;
import java.util.List;

class PaymentOverview {
    private double totalAmount;
    private int totalPayments;
    private Date lastPaymentDate;

    public void updateOverview(List<Payment> payments) {
        totalAmount = payments.stream().mapToDouble(Payment::getAmount).sum();
        totalPayments = payments.size();
        lastPaymentDate = payments.stream()
                .map(Payment::getPaymentDate)
                .max(Date::compareTo)
                .orElse(null);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getTotalPayments() {
        return totalPayments;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }
}
