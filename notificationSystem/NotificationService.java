package notificationSystem;


import java.util.Date;
public class NotificationService {
    
    // Inject repositories for User, Payment, Notification (e.g., via @Autowired in Spring)

    public void sendPaymentNotification(Payment payment) {
        // Notify sender
        Notification senderNotification = new Notification();
        senderNotification.setSender(payment.getSender());
        senderNotification.setPaymentId(payment.getPaymentId());
        senderNotification.setMessage("Your payment of " + payment.getAmount() + " was successful.");
        senderNotification.setPaymentDate(new Date());
        // Save senderNotification to the database
        
        // Notify receiver
        Notification receiverNotification = new Notification();
        receiverNotification.setReceiver(payment.getReceiver());
        receiverNotification.setPaymentId(payment.getPaymentId());
        receiverNotification.setMessage("You received a payment of " + payment.getAmount() + ".");
        receiverNotification.setPaymentDate(new Date());
        // Save receiverNotification to the database
    }
   public static void main(String[] args) {
	   User sender=new User(123l, "user1", "user1@mail.com", "989893593");
	   User receiver=new User(111l,"user2","user2@mail.com","7348557932");
	   Payment pay=new Payment(345678l,4235.90, new Date(), "Done",  sender,  receiver);
	   NotificationService service=new NotificationService();
 	    service.sendPaymentNotification(pay);
   }
}
