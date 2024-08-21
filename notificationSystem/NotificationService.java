package notificationSystem;

public class NotificationService {
    
    // Inject repositories for User, Payment, Notification (e.g., via @Autowired in Spring)

    public void sendPaymentNotification(Payment payment) {
        // Notify sender
        Notification senderNotification = new Notification();
        senderNotification.setUser(payment.getSender());
        senderNotification.setPayment(payment);
        senderNotification.setMessage("Your payment of " + payment.getAmount() + " was successful.");
        senderNotification.setSentDate(new Date());
        senderNotification.setIsRead(false);
        // Save senderNotification to the database
        
        // Notify receiver
        Notification receiverNotification = new Notification();
        receiverNotification.setUser(payment.getReceiver());
        receiverNotification.setPayment(payment);
        receiverNotification.setMessage("You received a payment of " + payment.getAmount() + ".");
        receiverNotification.setSentDate(new Date());
        receiverNotification.setIsRead(false);
        // Save receiverNotification to the database
    }
}
