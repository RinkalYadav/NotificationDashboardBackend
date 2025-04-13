package in.sp.main.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entities.Notification;
import in.sp.main.services.NotificationService;

@CrossOrigin(origins = "*")
@RestController
public class MyController {
	
	 @Autowired
	 private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private NotificationService notificationService;

	@PostMapping("/notification")
	public Notification addNotification(@RequestBody Notification notification) {
		 
		Notification saved = notificationService.createNotification(notification);
		messagingTemplate.convertAndSend("/topic/notifications", saved);
		return saved;
	}
	
	@GetMapping("/notification")
	public List<Notification> getAllNotificationsDetails() {
		return notificationService.getAllnotifications();
	}
	
	@PutMapping("/notification/{srno}")
	public ResponseEntity<Notification> updateNotificationDetails(@PathVariable int srno, @RequestBody Notification notification) {
		Notification updatedNotification = notificationService.updateNotificationDetails(srno, notification);
		 if (updatedNotification != null) {
		        updatedNotification.setType("UPDATE");
		        updatedNotification.setTimestamp(LocalDateTime.now());
		        
		        messagingTemplate.convertAndSend("/topic/notifications", updatedNotification);
		        return ResponseEntity.ok(updatedNotification);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
	}
	
	@DeleteMapping("/notification/{srno}")
	public ResponseEntity<Void> deleteNotification(@PathVariable int srno){
		notificationService.deleteNotification(srno);

	    // Create a dummy Notification object to indicate deletion
	    Notification deletedNotification = new Notification();
	    deletedNotification.setSrno(srno);
	    deletedNotification.setType("DELETE");

	    messagingTemplate.convertAndSend("/topic/notifications", deletedNotification);

	    return ResponseEntity.noContent().build();
	}
}
  