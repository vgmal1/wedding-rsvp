package wedding.rsvp.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import wedding.rsvp.entity.Invitee;
import wedding.rsvp.entity.InviteeMeal;

@Data
@NoArgsConstructor
public class InviteeMealData {
	private Long inviteeMealId;
	private String mealChoice;
	private Set<InviteeData> invitees = new HashSet<>();

	public InviteeMealData(InviteeMeal inviteeMeal) {
		this.inviteeMealId = inviteeMeal.getInviteeMealId();
		this.mealChoice = inviteeMeal.getMealChoice();

		for (Invitee invitee : inviteeMeal.getInvitees()) {
			this.invitees.add(new InviteeData(invitee));
		}
	}

	public InviteeMealData(Long inviteeMealId, String mealChoice) {
		this.inviteeMealId = inviteeMealId;
		this.mealChoice = mealChoice;
	}

	public InviteeMeal toInviteeMeal() {
		InviteeMeal inviteeMeal = new InviteeMeal();

		inviteeMeal.setInviteeMealId(inviteeMealId);
		inviteeMeal.setMealChoice(mealChoice);

		for (InviteeData inviteeData : invitees) {
			inviteeMeal.getInvitees().add(inviteeData.toInvitee());
		}

		return inviteeMeal;
	}
}
