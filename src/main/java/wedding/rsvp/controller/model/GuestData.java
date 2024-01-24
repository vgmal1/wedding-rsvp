package wedding.rsvp.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import wedding.rsvp.entity.Guest;

@Data
@NoArgsConstructor
public class GuestData {
	private Long guestId;
	private String name;

	public GuestData(Guest guest) {
		this.guestId = guest.getGuestId();
		this.name = guest.getName();

	}
	
	public GuestData(Long guestId, String name) {
		this.guestId = guestId;
		this.name = name;

	}

	public Guest toGuest() {
		Guest guest = new Guest();

		guest.setGuestId(guestId);
		guest.setName(name);

		return guest;
	}
}
