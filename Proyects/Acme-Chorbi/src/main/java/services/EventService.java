
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Chorbi;
import domain.Event;
import domain.Manager;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository	eventRepository;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private ChirpService	chirpService;


	public EventService() {
		super();
	}

	public Event create() {
		final Event result = new Event();
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		result.setOrganiser(manager);
		result.setRegistered(new ArrayList<Chorbi>());
		return result;
	}

	public Event findOne(final int eventId) {
		Event result;

		result = this.eventRepository.findOne(eventId);

		return result;
	}

	public Event findOneToEdit(final int eventId) {
		Event result;

		result = this.eventRepository.findOne(eventId);
		this.checkPrincipal(result);

		return result;
	}

	public Collection<Event> findAll() {
		return this.eventRepository.findAll();
	}

	public Collection<Event> findAllEventsInOneMonth() {
		Collection<Event> result;
		result = this.eventRepository.findAllEventsInOneMonth();
		return result;
	}

	public Collection<Event> findAllByChorbi(final int chorbiId) {
		Collection<Event> result;
		result = this.eventRepository.findAllByChorbi(chorbiId);
		return result;
	}

	public Collection<Event> findAllByPrincipal() {
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Collection<Event> result;
		result = this.eventRepository.findAllByPrincipal(manager.getId());
		return result;
	}

	public Event save(final Event event) {
		Assert.notNull(event);
		Event result;
		if (event.getId() != 0)
			this.chirpService.automaticChirp(event);
		result = this.eventRepository.save(event);
		return result;
	}

	public void delete(final Event event) {

		Assert.notNull(event);
		Assert.isTrue(event.getId() != 0);
		this.checkPrincipal(event);
		this.managerService.checkPrincipal();

		this.eventRepository.delete(event);

		Assert.isNull(this.eventRepository.findOne(event.getId()));

	}

	public void flush() {
		this.eventRepository.flush();

	}

	public void register(final Event event) {
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Assert.isTrue(event.getMoment().compareTo(new Date()) > 0);
		final Collection<Chorbi> registered = event.getRegistered();
		if (event.getRegistered().contains(chorbi)) {
			registered.remove(chorbi);
			event.setRegistered(registered);
		} else {
			registered.add(chorbi);
			event.setRegistered(registered);
		}
		this.save(event);
	}

	public void checkPrincipal(final Event event) {
		final Manager manager = this.managerService.findByPrincipal();

		Assert.isTrue(manager.equals(event.getOrganiser()));
	}

	public boolean checkAttachment(final String attachment) {

		boolean result = false;
		if (attachment.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))
			result = true;

		return result;
	}

	public Collection<Event> findAllByPrincipalChorbi() {
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Collection<Event> result;
		result = this.eventRepository.findAllByChorbi(chorbi.getId());
		return result;
	}

}
