package in.sp.main.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
 private int srno;
	@Column
 private String message;
	@Column
 private String category;
	
	 @CreationTimestamp
	 @Column
	private LocalDateTime timestamp;

public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
public int getSrno() {
	return srno;
}
public void setSrno(int srno) {
	this.srno = srno;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

 
}
