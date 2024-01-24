package wedding.rsvp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import wedding.rsvp.entity.Guest;

public interface GuestDao extends JpaRepository<Guest, Long> {

}
