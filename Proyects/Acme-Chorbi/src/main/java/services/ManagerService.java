
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ManagerRepository;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	@Autowired
	private ManagerRepository	managerRepository;


	public ManagerService() {
		super();
	}

	public Collection<Manager> findAll() {
		Collection<Manager> result;
		result = this.managerRepository.findAll();
		return result;
	}

	//Dashboard

	// A listing of managers sorted by the number of events that they organise.
	public Collection<Manager> findManagersOrderByEvent() {
		Collection<Manager> result;
		result = this.managerRepository.findManagersOrderByEvent();
		return result;
	}

}
