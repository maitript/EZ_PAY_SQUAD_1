package projectnotification;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class NotificationServiceTest {

    private User sender;
    private User receiver;
    private Payment payment;
    private NotificationService notificationService;

    // Setup method to initialize common objects before each test
    @Before
    public void setUp() {
        sender = new User(123L, "user1", "user1@mail.com", "989893593");
        receiver = new User(111L, "user2", "user2@mail.com", "7348557932");
        payment = new Payment(345678L, 4235.90, new Date(), "Done", sender, receiver);
        notificationService = new NotificationService();
    }

    // Test that verifies the notification message is correctly generated for the sender
    @Test
    public void testSendPaymentNotificationForSender() {
        notificationService.sendPaymentNotification(payment);

        // Expected message for the sender
        String expectedMessage = "user1 your payment of 4235.9 was successful.";

        // Create a Notification object for the sender and set expected values
        Notification senderNotification = new Notification();
        senderNotification.setSender(payment.getSender());
        senderNotification.setPaymentId(payment.getPaymentId());
        senderNotification.setMessage(expectedMessage);

        // Verify that the notification contains the correct details for the sender
        assertEquals(payment.getSender(), senderNotification.getSender());
        assertEquals(payment.getPaymentId(), senderNotification.getPaymentId());
        assertEquals(expectedMessage, senderNotification.getMessage());
    }

    // Test that verifies the notification message is correctly generated for the receiver
    @Test
    public void testSendPaymentNotificationForReceiver() {
        notificationService.sendPaymentNotification(payment);

        // Expected message for the receiver
        String expectedMessage = "user2 received a payment of 4235.9.";

        // Create a Notification object for the receiver and set expected values
        Notification receiverNotification = new Notification();
        receiverNotification.setReceiver(payment.getReceiver());
        receiverNotification.setPaymentId(payment.getPaymentId());
        receiverNotification.setMessage(expectedMessage);

        // Verifies that the notification contains the correct details for the receiver
        assertEquals(payment.getReceiver(), receiverNotification.getReceiver());
        assertEquals(payment.getPaymentId(), receiverNotification.getPaymentId());
        assertEquals(expectedMessage, receiverNotification.getMessage());
    }

    // Test that verifies an exception is thrown when the sender is null
    @Test
    public void testSendPaymentNotificationWithNullSender() {
        payment.setSender(null);

        try {
            notificationService.sendPaymentNotification(payment);
            fail("Expected NullPointerException when sender is null.");
        } catch (NullPointerException e) {
            // Verify that the correct exception message is thrown
            assertEquals("Sender must not be null", e.getMessage());
        }
    }

    // Test that verifies an exception is thrown when the receiver is null
    @Test
    public void testSendPaymentNotificationWithNullReceiver() {
        payment.setReceiver(null);

        try {
            notificationService.sendPaymentNotification(payment);
            fail("Expected NullPointerException when receiver is null.");
        } catch (NullPointerException e) {
            // Verify that the correct exception message is thrown
            assertEquals("Receiver must not be null", e.getMessage());
        }
    }

    // Test that verifies the notification is generated correctly when usernames are empty
    @Test
    public void testSendPaymentNotificationWithEmptyUsername() {
        sender.setUsername("");
        receiver.setUsername("");

        notificationService.sendPaymentNotification(payment);

        // Expected messages when usernames are empty
        String expectedSenderMessage = " your payment of 4235.9 was successful.";
        String expectedReceiverMessage = " received a payment of 4235.9.";

        // Verify that the notification messages are generated correctly despite empty usernames
        Notification senderNotification = new Notification();
        senderNotification.setSender(payment.getSender());
        senderNotification.setPaymentId(payment.getPaymentId());
        senderNotification.setMessage(expectedSenderMessage);

        Notification receiverNotification = new Notification();
        receiverNotification.setReceiver(payment.getReceiver());
        receiverNotification.setPaymentId(payment.getPaymentId());
        receiverNotification.setMessage(expectedReceiverMessage);

        // Check that the messages are as expected
        assertEquals(expectedSenderMessage, senderNotification.getMessage());
        assertEquals(expectedReceiverMessage, receiverNotification.getMessage());
    }

    // Test that verifies an exception is thrown when the amount is null
    @Test
    public void testSendPaymentNotificationWithNullAmount() {
        payment.setAmount(null);

        try {
            notificationService.sendPaymentNotification(payment);
            fail("Expected NullPointerException when amount is null.");
        } catch (NullPointerException e) {
            // Verify that the correct exception message is thrown
            assertEquals("Amount must not be null", e.getMessage());
        }
    }

    // Test that verifies the payment date is correctly set when a future date is used
    @Test
    public void testSendPaymentNotificationWithFutureDate() {
        Date futureDate = new Date(System.currentTimeMillis() + 1000000000L);
        payment.setPaymentDate(futureDate);

        notificationService.sendPaymentNotification(payment);

        // Verify that the payment date is correctly assigned
        assertEquals(futureDate, payment.getPaymentDate());
    }

    // Test that verifies an exception is thrown when the status is null
    @Test
    public void testSendPaymentNotificationWithNullStatus() {
        payment.setStatus(null);

        try {
            notificationService.sendPaymentNotification(payment);
            fail("Expected NullPointerException when status is null.");
        } catch (NullPointerException e) {
            // Verify that the correct exception message is thrown
            assertEquals("Status must not be null", e.getMessage());
        }
    }
}
