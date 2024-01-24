package wedding.rsvp.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class InviteeMeal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inviteeMealId;
	
	private String mealChoice;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "inviteeMeals")
	private Set<Invitee> invitees = new HashSet<Invitee>();
	
}
