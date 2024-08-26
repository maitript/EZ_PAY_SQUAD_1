package myPack;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentOverview {
    private BigDecimal totalPaid;
    private BigDecimal totalReceived;
    private Timestamp lastPaymentDate;

    // Constructor
    public PaymentOverview(BigDecimal totalPaid, BigDecimal totalReceived, Timestamp lastPaymentDate) {
        this.totalPaid = totalPaid;
        this.totalReceived = totalReceived;
        this.lastPaymentDate = lastPaymentDate;
    }

    // Getters and Setters
    public BigDecimal getTotalPaid() { return totalPaid; }
    public void setTotalPaid(BigDecimal totalPaid) { this.totalPaid = totalPaid; }

    public BigDecimal getTotalReceived() { return totalReceived; }
    public void setTotalReceived(BigDecimal totalReceived) { this.totalReceived = totalReceived; }

    public Timestamp getLastPaymentDate() { return lastPaymentDate; }
    public void setLastPaymentDate(Timestamp lastPaymentDate) { this.lastPaymentDate = lastPaymentDate; }
}
