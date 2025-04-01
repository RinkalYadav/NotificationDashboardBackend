package in.sp.main.services;

import java.util.List;

import in.sp.main.entities.Notification;

public interface NotificationService {
	public Notification createNotification(Notification notification);
	public List<Notification> getAllnotifications();
 
}
