package myPack;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentHistory {
    private int paymentId;
    private int userId;
    private BigDecimal amount;
    private Timestamp paymentDate;
    private String description;

    // Constructor
    public PaymentHistory(int paymentId, int userId, BigDecimal amount, Timestamp paymentDate, String description) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.description = description;
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Timestamp getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Timestamp paymentDate) { this.paymentDate = paymentDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
