package dashboardexample;
import  java.util.Date;

public class Payment {
    private Long paymentId;
    private Double amount;
    private Date paymentDate;
    private String status;
    private User sender;
    private User receiver;

    public Payment(Long paymentId, Double amount, Date paymentDate, String status, User sender, User receiver) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", paymentDate=" + paymentDate + ", status="
                + status + ", sender=" + sender + ", receiver=" + receiver + "]";
    }
}
