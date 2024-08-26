package EZPay.UI.noti;

import EZPay.UI.noti.Notification;
import java.util.List;

public interface NotificationDAO {
    void save(Notification notification);
    Notification findById(Long id);
    List<Notification> findAll();
    void update(Notification notification);
    void delete(Long id);
}
