package in.sp.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entities.Notification;
import in.sp.main.services.NotificationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MyController {
	
	@Autowired
	private NotificationService notificationService;

	@PostMapping("/notification")
	public Notification addNotification(@RequestBody Notification notification) {
		return notificationService.createNotification(notification);
	}
	
	@GetMapping("/notification")
	public List<Notification> getAllNotificationsDetails() {
		return notificationService.getAllnotifications();
	}
}
 