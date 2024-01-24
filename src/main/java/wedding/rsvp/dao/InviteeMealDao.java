package wedding.rsvp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import wedding.rsvp.entity.InviteeMeal;

public interface InviteeMealDao extends JpaRepository<InviteeMeal, Long> {
	
}
