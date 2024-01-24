package wedding.rsvp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long guestId;
	
	@EqualsAndHashCode.Exclude
	private String name;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "invitee_id", nullable = false)
	private Invitee invitee;
	
}
