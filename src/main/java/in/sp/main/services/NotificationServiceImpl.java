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
}
