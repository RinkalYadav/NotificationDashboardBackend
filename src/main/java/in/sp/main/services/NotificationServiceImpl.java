package in.sp.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Notification;
import in.sp.main.repositories.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public Notification createNotification(Notification notification) {
		
		return notificationRepository.save(notification);
	}

	@Override
	public List<Notification> getAllnotifications() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification updateNotificationDetails(int srno, Notification newNotification) {
		Notification notificationData = notificationRepository.findById(srno).orElse(null);
		
		if(notificationData != null){
			return notificationRepository.save(newNotification);
		}
		else {
			throw new RuntimeException("User not found with srno "+srno);
		}
		
	}

	@Override
	public void deleteNotification(int srno) {
		notificationRepository.deleteById(srno);
		
	}
	
	@Override
	public Notification getNotificationById(int srno) {
	    return notificationRepository.findById(srno).orElse(null);
	}

}
