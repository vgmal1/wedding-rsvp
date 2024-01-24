package wedding.rsvp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import wedding.rsvp.entity.Invitee;

public interface InviteeDao extends JpaRepository<Invitee, Long> {

}
