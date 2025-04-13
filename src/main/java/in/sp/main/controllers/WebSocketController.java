package in.sp.main.controllers;

import in.sp.main.entities.Notification;
import in.sp.main.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class WebSocketController {

    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void handleNotification(Notification notification) {
        // Set timestamp if not provided
        if (notification.getTimestamp() == null) {
            notification.setTimestamp(LocalDateTime.now());
        }
        
        // Save to database
        Notification savedNotification = notificationRepository.save(notification);
        System.out.println("Saved notification: " + savedNotification);
        
        // Broadcast to all subscribers
        messagingTemplate.convertAndSend("/topic/notifications", savedNotification);
        System.out.println("Broadcasted to /topic/notifications");
    }
}