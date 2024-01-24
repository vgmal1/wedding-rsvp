package wedding.rsvp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wedding.rsvp.controller.model.GuestData;
import wedding.rsvp.controller.model.InviteeData;
import wedding.rsvp.controller.model.InviteeMealData;
import wedding.rsvp.dao.GuestDao;
import wedding.rsvp.dao.InviteeDao;
import wedding.rsvp.dao.InviteeMealDao;
import wedding.rsvp.entity.Guest;
import wedding.rsvp.entity.Invitee;
import wedding.rsvp.entity.InviteeMeal;

@Service
public class RsvpService {

	@Autowired
	private InviteeDao inviteeDao;

	@Autowired
	private GuestDao guestDao;

	@Autowired
	private InviteeMealDao inviteeMealDao;

	@Transactional(readOnly = false)
	public InviteeData saveInvitee(InviteeData inviteeData) {
		Invitee invitee = inviteeData.toInvitee();
		Invitee dbInvitee = inviteeDao.save(invitee);

		return new InviteeData(dbInvitee);

	}

	@Transactional(readOnly = true)
	public InviteeData retrieveInviteeById(Long inviteeId) {
		Invitee invitee = findInviteeById(inviteeId);
		return new InviteeData(invitee);
	}

	private Invitee findInviteeById(Long inviteeId) {
		return inviteeDao.findById(inviteeId)
				.orElseThrow(() -> new NoSuchElementException("Invitee " + inviteeId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<InviteeData> retrieveAllInvitees() {
		List<Invitee> inviteeEntities = inviteeDao.findAll();
		List<InviteeData> inviteeDtos = new LinkedList<>();

		for (Invitee invitee : inviteeEntities) {
			InviteeData inviteeData = new InviteeData(invitee);
			inviteeDtos.add(inviteeData);
		}

		return inviteeDtos;
	}

	@Transactional(readOnly = false)
	public void deleteInvitee(Long inviteeId) {
		Invitee invitee = findInviteeById(inviteeId);
		inviteeDao.delete(invitee);

	}

	@Transactional(readOnly = false)
	public GuestData saveGuest(Long inviteeId, GuestData guestData) {
		Invitee invitee = findInviteeById(inviteeId);

		Guest guest = guestData.toGuest();

		guest.setInvitee(invitee);
		invitee.getGuests().add(guest);

		Guest dbGuest = guestDao.save(guest);

		return new GuestData(dbGuest);

	}

	@Transactional(readOnly = true)
	public GuestData retrieveGuestById(Long inviteeId, Long guestId) {
		findInviteeById(inviteeId);
		Guest guest = findGuestById(guestId);

		if (guest.getInvitee().getInviteeId() != inviteeId) {
			throw new IllegalStateException("Guest with ID=" + guestId + " does not go with ID=" + inviteeId);
		}
		return new GuestData(guest);
	}

	private Guest findGuestById(Long guestId) {
		return guestDao.findById(guestId)
				.orElseThrow(() -> new NoSuchElementException("Guest " + guestId + " was not found."));
	}

	@Transactional(readOnly = false)
	public void deleteGuest(Long inviteeId, Long guestId) {
		findInviteeById(inviteeId);
		Guest guest = findGuestById(guestId);
		guestDao.delete(guest);

	}


	@Transactional(readOnly = false)
	public InviteeMealData saveInviteeMeal(Long inviteeId, InviteeMealData inviteeMealData) {
		Invitee invitee = findInviteeById(inviteeId);

		InviteeMeal inviteeMeal = inviteeMealData.toInviteeMeal();

		invitee.getInviteeMeals().add(inviteeMeal);

		InviteeMeal dbInviteeMeal = inviteeMealDao.save(inviteeMeal);

		return new InviteeMealData(dbInviteeMeal);
	}


	@Transactional(readOnly = true)
	public List<InviteeMealData> retrieveAllInviteeMeals() {
		List<InviteeMeal> inviteeMealEntities = inviteeMealDao.findAll();
		List<InviteeMealData> inviteeMealDtos = new LinkedList<InviteeMealData>();

		for (InviteeMeal inviteeMeal : inviteeMealEntities) {
			InviteeMealData inviteeMealData = new InviteeMealData(inviteeMeal);
			inviteeMealDtos.add(inviteeMealData);
		}

		return inviteeMealDtos;
	}
}

