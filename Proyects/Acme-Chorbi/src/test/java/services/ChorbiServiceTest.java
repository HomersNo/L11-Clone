
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChorbiServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------------------
	@Autowired
	private ChorbiService	chorbiService;

	Calendar				calendarValida	= new GregorianCalendar(1995, 12, 14);
	Date					fechaValida		= this.calendarValida.getTime();

	Calendar				calendarFutura	= new GregorianCalendar(2020, 12, 14);
	Date					fechaFutura		= this.calendarFutura.getTime();


	// Teoria pagina 107 y 108
	// Tests ---------------------------------------------------------------
	@Test
	public void driverCreation() {
		final Object testingData[][] = {
			{		// Creación correcta de un Customer.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", null
			}, {	// Creación errónea de un Customer: username vacío.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: password vacío.
				"correcto", "", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: name vacío.
				"correcto", "correcto", "", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: surname vacío.
				"correcto", "correcto", "correcto", "", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: email vacío.
				"correcto", "correcto", "correcto", "correcto", "", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: phoneNumber vacío.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: username con pocos carácteres.
				"cor", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: password con pocos carácteres.
				"correcto", "cor", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: email incorrecto.
				"correcto", "correcto", "correcto", "correcto", "correctobien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: phoneNumber incorrecto.
				"correcto", "correcto", "correcto", "correcto", "correcto@bien.com", "A", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: picture vacío.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: picture incorrecto.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", ".edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: description vacío.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: relationship vacío.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: relationship incorrecto.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: fecha vacía.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", null, "WOMAN", false, "Country", "state", "province", "city", IllegalArgumentException.class
			}, {	// Creación errónea de un Customer: fecha futura.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaFutura, "WOMAN", false, "Country", "state", "province", "city", IllegalArgumentException.class
			}, {	// Creación errónea de un Customer: genre vacío.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: genre incorrecto.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WMAN", false, "Country", "state", "province", "city", ConstraintViolationException.class
			}, {	// Creación errónea de un Customer: ciudad vacía.
				"", "correcto", "correcto", "correcto", "correcto@bien.com", "1234", "http://www.edurne.com", "descripcion", "LOVE", this.fechaValida, "WOMAN", false, "Country", "state", "province", "", ConstraintViolationException.class

			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreation((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (Date) testingData[i][9], (String) testingData[i][10], (Boolean) testingData[i][11], (String) testingData[i][12], (String) testingData[i][13], (String) testingData[i][14],
				(String) testingData[i][15], (Class<?>) testingData[i][16]);
	}
	@Test
	public void driverDisplaying() {
		final Object testingData[][] = {
			{		// Display correcto de un Customer ya creado y logueado como tal. 
				"chorbi1", 47, null
			}, {	// Display correcto de un Customer distinto al que está logueado.
				"chorbi1", 48, null
			}, {	// Display erróneo de un Customer que no existe con uno logueado.
				"chorbi1", 100, IllegalArgumentException.class
			}, {	// Display correcto de un Customer, sin estar logueado en el sistema.
				null, 49, null
			}, {	// Display erróneo de un Customer que no existe sin estar logueado.
				null, 100, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateDisplaying((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Templates ----------------------------------------------------------
	protected void templateCreation(final String username, final String password, final String name, final String surname, final String email, final String phone, final String picture, final String description, final String relationshipType,
		final Date birthDate, final String genre, final Boolean banned, final String country, final String state, final String province, final String city, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final Chorbi c = this.chorbiService.create();
			final UserAccount user = new UserAccount();
			final Collection<Authority> as = new ArrayList<Authority>();
			final Authority a = new Authority();
			a.setAuthority("CHORBI");
			as.add(a);
			user.setAuthorities(as);
			c.setUserAccount(user);
			user.setUsername(username);
			user.setPassword(password);
			c.setUserAccount(user);
			c.setName(name);
			c.setSurname(surname);
			c.setEmail(email);
			c.setPhoneNumber(phone);
			c.setPicture(picture);
			c.setDescription(description);
			c.setRelationshipType(relationshipType);
			c.setBirthDate(birthDate);
			c.setGenre(genre);
			c.setBanned(banned);
			c.setCountry(country);
			c.setState(state);
			c.setProvince(province);
			c.setCity(city);
			this.chorbiService.save(c);
			this.chorbiService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDisplaying(final String username, final int chorbiId, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);
			final Chorbi c = this.chorbiService.findOne(chorbiId);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
