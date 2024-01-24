package wedding.rsvp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wedding.rsvp.controller.model.GuestData;
import wedding.rsvp.controller.model.InviteeData;
import wedding.rsvp.controller.model.InviteeMealData;
import wedding.rsvp.service.RsvpService;

@RestController
@RequestMapping("/wedding_rsvp")
@Slf4j
public class RsvpController {
	@Autowired
	private RsvpService rsvpService;

	// Invitee Table

	@PostMapping("/invitee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public InviteeData createInvitee(@RequestBody InviteeData inviteeData) {
		log.info("Creating invitee {}", inviteeData);
		return rsvpService.saveInvitee(inviteeData);
	}

	@PutMapping("/invitee/{inviteeId}")
	public InviteeData updateInvitee(@PathVariable Long inviteeId, @RequestBody InviteeData inviteeData) {
		inviteeData.setInviteeId(inviteeId);
		log.info("Updating invitee {}", inviteeData);
		return rsvpService.saveInvitee(inviteeData);
	}

	@GetMapping("/invitee/{inviteeId}")
	public InviteeData retrieveInviteeById(@PathVariable Long inviteeId) {
		log.info("Retrieving invitee {}", inviteeId);
		return rsvpService.retrieveInviteeById(inviteeId);
	}

	@GetMapping("/invitee")
	public List<InviteeData> retrieveAllInvitees() {
		log.info("Retrieving all invitees");
		return rsvpService.retrieveAllInvitees();
	}

	@DeleteMapping("/invitee/{inviteeId}")
	public Map<String, String> deleteInvitee(@PathVariable Long inviteeId) {
		log.info("Deleting invitee " + inviteeId + ".");
		rsvpService.deleteInvitee(inviteeId);

		return Map.of("message", "invitee " + inviteeId + " was deleted successfully.");
	}

	// Guest Table

	@PostMapping("invitee/{inviteeId}/guest")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GuestData createGuest(@PathVariable Long inviteeId, @RequestBody GuestData guestData) {
		log.info("Creating guest {} for invitee {}", guestData, inviteeId);
		return rsvpService.saveGuest(inviteeId, guestData);
	}

	@PutMapping("invitee/{inviteeId}/guest/{guestId}")
	public GuestData updateGuest(@PathVariable Long inviteeId, @PathVariable Long guestId,
			@RequestBody GuestData guestData) {
		guestData.setGuestId(guestId);

		log.info("Updating guest {} for invitee {}", guestData, inviteeId);
		return rsvpService.saveGuest(inviteeId, guestData);
	}

	@GetMapping("invitee/{inviteeId}/guest/{guestId}")
	public GuestData retrieveGuestById(@PathVariable Long inviteeId, @PathVariable Long guestId) {
		log.info("Retrieving guest {} for invitee {}", guestId, inviteeId);
		return rsvpService.retrieveGuestById(inviteeId, guestId);
	}

	@DeleteMapping("invitee/{inviteeId}/guest/{guestId}")
	public Map<String, String> deleteGuest(@PathVariable Long inviteeId, @PathVariable Long guestId) {
		log.info("Deleting guest " + guestId + "for invitee " + inviteeId + ".");
		rsvpService.deleteGuest(inviteeId, guestId);

		return Map.of("message", "guest " + guestId + " for invitee " + inviteeId + " was deleted successfully.");
	}

	
	// InviteeMeal Table
	
	
	@PostMapping("invitee/{inviteeId}/inviteeMeal")
	@ResponseStatus(code = HttpStatus.CREATED)
	public InviteeMealData createInviteeMeal(@PathVariable Long inviteeId, @RequestBody InviteeMealData inviteeMealData) {
		log.info("Creating inviteeMeal {} for invitee {}", inviteeMealData, inviteeId);
		return rsvpService.saveInviteeMeal(inviteeId, inviteeMealData);
	}

	@GetMapping("/inviteeMeal")
	public List<InviteeMealData> retrieveAllInviteeMeals() {
		log.info("Retrieving all inviteeMeals");
		return rsvpService.retrieveAllInviteeMeals();
	}
}
