package EZPay.UI.noti;
import java.util.Date;
public class NotificationService {
    public void sendPaymentNotification(Payment payment) {
        // Notify sender
        Notification senderNotification = new Notification();
        senderNotification.setSender(payment.getSender());
        senderNotification.setPaymentId(payment.getPaymentId());
        senderNotification.setMessage(payment.getSender().getUsername()+" your payment of " + payment.getAmount() + " was successful.");
        senderNotification.setPaymentDate(new Date());
        System.out.println(senderNotification.getMessage());;
        // Save senderNotification to the database
        
        // Notify receiver
        Notification receiverNotification = new Notification();
        receiverNotification.setReceiver(payment.getReceiver());
        receiverNotification.setPaymentId(payment.getPaymentId());
        receiverNotification.setMessage(payment.getReceiver().getUsername() +" received a payment of " + payment.getAmount() + ".");
        receiverNotification.setPaymentDate(new Date());
        System.out.println(receiverNotification.getMessage());;
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
