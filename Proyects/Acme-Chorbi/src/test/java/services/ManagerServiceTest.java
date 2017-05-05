package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Chorbi;
import domain.Event;
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})

@Transactional
public class ManagerServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------------------
	@Autowired
	private ManagerService	managerService;

	// Teoria pagina 107 y 108
	// Tests ---------------------------------------------------------------
	@Test
	public void driverCreation() {
		final Object testingData[][] = {
			{		// Creación correcta de un Manager.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321", null
			}, {	// Creación errónea de un Manager: title vacío.
				"correcto", "correcto", "", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}, {	// Creación errónea de un Manager: description vacío.
				"correcto", "correcto", "correcto", "", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321",  ConstraintViolationException.class
			}, {	// Creación errónea de un Manager: picture que no es url.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}, {	// Creación errónea de un Manager: moment nulo vacío.
				"correcto", "", "correcto", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321",  ConstraintViolationException.class
			}, {	// Creación errónea de un Manager: numberSeat negativo.
				"correcto", "correcto", "correcto", "correcto", "", "1234", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}, {	// Creación errónea de un Manager: no es un manager el logueado.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "", "equisdejajajaxdxdxd", "43321",  ConstraintViolationException.class
			}, {		// Creación correcta de un Manager.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "", "43321", ConstraintViolationException.class
			}, {		// Creación correcta de un Manager.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "", ConstraintViolationException.class
			}, {		// Creación correcta de un Manager.
				"cor", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}, {		// Creación correcta de un Manager.
				"correcto", "co", "correcto", "correcto", "correcto@bien.com", "1234", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}, {		// Creación correcta de un Manager.
				"correcto", "correcto", "correcto", "correcto", "ajjj", "1234", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}, {		// Creación correcta de un Manager.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "A", "equisdejajajaxdxdxd", "43321", ConstraintViolationException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreationDelete((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7], (Class<?>) testingData[i][8]);
	}
		
	// Templates ----------------------------------------------------------
	private void templateCreationDelete(final String username, final String password, final String name, final String surname, final String email, final String phone,
			final String companyName, final String VATNumber, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final Manager m = this.managerService.create();
			final UserAccount user = new UserAccount();
			final Collection<Authority> as = new ArrayList<Authority>();
			final Authority a = new Authority();
			a.setAuthority("MANAGER");
			as.add(a);
			user.setAuthorities(as);
			m.setUserAccount(user);
			user.setUsername(username);
			user.setPassword(password);
			m.setUserAccount(user);
			m.setName(name);
			m.setSurname(surname);
			m.setEmail(email);
			m.setPhoneNumber(phone);
			
			m.setCompanyName(companyName);
			m.setVATNumber(VATNumber);
			this.managerService.save(m);
			this.managerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}