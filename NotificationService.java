package EZPay.UI.noti;

import java.util.Date;
import java.util.*;
import EZPay.UI.noti.NotificationDAO;
import EZPay.UI.noti.NotificationDAOImpl;

public class NotificationService {

    //Implements the notificationDAO interface
    private NotificationDAO notificationDAO;

    public NotificationService() {
        this.notificationDAO = new NotificationDAOImpl();
    }

    public void sendPaymentNotification(Payment payment) {
        // Notify sender
        Notification senderNotification = new Notification();
        senderNotification.setSender(payment.getSender());
        senderNotification.setReceiver(payment.getReceiver());
        senderNotification.setPaymentId(payment.getPaymentId());
        senderNotification.setMessage(payment.getSender().getUsername() + " your payment of " + payment.getAmount() + " was successful.");
        senderNotification.setPaymentDate(new Date());
        senderNotification.setAmount(payment.getAmount());
        senderNotification.setStatus("Successful");
        System.out.println(senderNotification.getMessage());
        notificationDAO.save(senderNotification);

        // Notify receiver
//        Notification receiverNotification = new Notification();
//        receiverNotification.setReceiver(payment.getReceiver());
//        receiverNotification.setPaymentId(payment.getPaymentId());
//        receiverNotification.setMessage(payment.getReceiver().getUsername() + " received a payment of " + payment.getAmount() + ".");
//        receiverNotification.setPaymentDate(new Date());
//        System.out.println(receiverNotification.getMessage());
//        notificationDAO.save(receiverNotification);
    }
    
    public void getNotification(Long Id) {
    	Notification notif = notificationDAO.findById(Id);
    	System.out.println(notif);
    }
    
//    public void updateNotification() {
//    	System.out.println("Enter the detail to be updated.Enter your choice");
//    	int ch = sc.next
//    	switch(ch)
//    }
    
    public void deleteNotification(Long Id) {
    	notificationDAO.delete(Id);
    	System.out.println("Notification deleted successfully");
    }
    public static void main(String[] args) {
        User sender = new User(123L, "user1", "user1@mail.com", "989893593");
        User receiver = new User(111L, "user2", "user2@mail.com", "7348557932");
        Payment pay = new Payment(345678L, 4235.90, new Date(), "Done", sender, receiver);
        NotificationService service = new NotificationService();
        
        // To insert in table
        //service.sendPaymentNotification(pay);
        
        //Fetch by id
        //service.getNotification(345678L);
        
        //Delete notification
        //service.deleteNotification(345678L);
    }
}
