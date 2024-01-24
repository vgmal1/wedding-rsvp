package wedding.rsvp.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import wedding.rsvp.entity.Guest;
import wedding.rsvp.entity.Invitee;

@Data
@NoArgsConstructor
public class InviteeData {
	private Long inviteeId;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String rsvpResponse;

	private Set<GuestData> guests = new HashSet<>();

	public InviteeData(Invitee invitee) {
		this.inviteeId = invitee.getInviteeId();
		this.name = invitee.getName();
		this.address = invitee.getAddress();
		this.city = invitee.getCity();
		this.state = invitee.getState();
		this.zip = invitee.getZip();
		this.rsvpResponse = invitee.getRsvpResponse();

		for (Guest guest : invitee.getGuests()) {
			this.guests.add(new GuestData(guest));
		}
	}

	public InviteeData(Long inviteeId, String name, String address, String city, String state, String zip,
			String rsvpResponse) {
		this.inviteeId = inviteeId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.rsvpResponse = rsvpResponse;
	}

	public Invitee toInvitee() {
		Invitee invitee = new Invitee();

		invitee.setInviteeId(inviteeId);
		invitee.setName(name);
		invitee.setAddress(address);
		invitee.setCity(city);
		invitee.setState(state);
		invitee.setZip(zip);
		invitee.setRsvpResponse(rsvpResponse);

		for (GuestData guestData : guests) {
			invitee.getGuests().add(guestData.toGuest());
		}

		return invitee;
	}

//	@Data
//	@NoArgsConstructor
//	public class GuestData {
//		private Long guestId;
//		private String name;
//		private Set<GuestMealData> guestMeals = new HashSet<>();
//
//		public GuestData(Guest guest) {
//			this.guestId = guest.getGuestId();
//			this.name = guest.getName();
//
//			for (GuestMeal guestMeal : guest.getGuestMeals()) {
//				this.guestMeals.add(new GuestMealData(guestMeal));
//			}
//		}
//
//		public Guest toGuest() {
//			Guest guest = new Guest();
//
//			guest.setGuestId(guestId);
//			guest.setName(name);
//
//			for (GuestMealData guestMealData : guestMeals) {
//				guest.getGuestMeals().add(guestMealData.toGuestMeal());
//			}
//
//			return guest;
//		}
//	}
//
//	@Data
//	@NoArgsConstructor
//	public class GuestMealData {
//		private Long guestMealId;
//		private String mealChoice;
//
//		public GuestMealData(GuestMeal guestMeal) {
//			this.guestMealId = guestMeal.getGuestMealId();
//			this.mealChoice = guestMeal.getMealChoice();
//		}
//
//		public GuestMeal toGuestMeal() {
//			GuestMeal guestMeal = new GuestMeal();
//
//			guestMeal.setGuestMealId(guestMealId);
//			guestMeal.setMealChoice(mealChoice);
//
//			return guestMeal;
//		}
//	}
//
//	@Data
//	@NoArgsConstructor
//	public class InviteeMealData {
//		private Long inviteeMealId;
//		private String mealChoice;
//
//		public InviteeMealData(InviteeMeal inviteeMeal) {
//			this.inviteeMealId = inviteeMeal.getInviteeMealId();
//			this.mealChoice = inviteeMeal.getMealChoice();
//		}
//
//		public InviteeMeal toInviteeMeal() {
//			InviteeMeal inviteeMeal = new InviteeMeal();
//
//			inviteeMeal.setInviteeMealId(inviteeMealId);
//			inviteeMeal.setMealChoice(mealChoice);
//
//			return inviteeMeal;
//		}
//	}

}
