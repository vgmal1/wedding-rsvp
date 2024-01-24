package wedding.rsvp.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Invitee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inviteeId;
	
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String rsvpResponse;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Guest> guests = new HashSet<Guest>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "invitee_meals_join",
			joinColumns = @JoinColumn(name = "invitee_id"),
			inverseJoinColumns = @JoinColumn(name = "invitee_meal_id")
		)
	private Set<InviteeMeal> inviteeMeals = new HashSet<>();
}
