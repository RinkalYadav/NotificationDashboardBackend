package in.sp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	
}  
