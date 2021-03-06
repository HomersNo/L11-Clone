
package services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Chorbi;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CreditCardServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------------------
	@Autowired
	private CreditCardService	creditCardService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ChorbiService		chorbiService;


	// Tests ---------------------------------------------------------------
	@Test
	public void driverCreationAndDelete() {
		final Object testingData[][] = {
			{		// Creaci�n correcta de una tarjeta de cr�dito.
				"chorbi4", "holderName", "VISA", "4662031000040705", 4, 19, 321, null
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: nombre vac�o.
				"chorbi4", "", "VISA", "4662031000040705", 4, 19, 321, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: marca vac�a.
				"chorbi4", "holderName", "", "4662031000040705", 4, 19, 321, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: n�mero vac�o.
				"chorbi4", "holderName", "VISA", "", 4, 19, 321, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: n�mero con formato incorrecto.
				"chorbi4", "holderName", "VISA", "4662031000040705", 4, 19, 321, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: mes incorrecto.
				"chorbi4", "holderName", "VISA", "4662031000040705", 15, 19, 321, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: a�o incorrecto.
				"chorbi4", "holderName", "VISA", "4662031000040705", 4, 234, 321, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: cvv incorrecto.
				"chorbi4", "holderName", "VISA", "4662031000040705", 4, 19, 3251, ConstraintViolationException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: chorbi incorrecto.
				"chorbi41", "holderName", "VISA", "4662031000040705", 4, 19, 321, IllegalArgumentException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: mes nulo.
				"chorbi4", "holderName", "VISA", "4662031000040705", null, 19, 321, NullPointerException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: a�o nulo.
				"chorbi4", "holderName", "VISA", "4662031000040705", 4, null, 321, NullPointerException.class
			}, {	// Creaci�n err�nea de una tarjeta de cr�dito: cvv nulo.
				"chorbi4", "holderName", "VISA", "4662031000040705", 4, 19, null, NullPointerException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreation((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Integer) testingData[i][4], (Integer) testingData[i][5], (Integer) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}
	@Test
	public void driverDisplaying() {
		final Object testingData[][] = {
			{		// Display correcto de una tarjeta de cr�dito del chorbi logeado.
				"chorbi1", "creditCard1", null
			}, {	// Display err�neo de una tarjeta de cr�dito inexistente.
				"chorbi1", "event1", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateDisplaying((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Templates ----------------------------------------------------------
	protected void templateCreation(final String username, final String holderName, final String brandName, final String creditCardNumber, final Integer expirationMonth, final Integer expirationYear, final Integer cvv, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);
			CreditCard cc = this.creditCardService.create();
			cc.setHolderName(holderName);
			cc.setBrandName(brandName);
			cc.setCreditCardNumber(creditCardNumber);
			cc.setExpirationMonth(expirationMonth);
			cc.setExpirationYear(expirationYear);
			cc.setCVV(cvv);
			final Chorbi c = (Chorbi) this.actorService.findByPrincipal();
			cc.setHolder(c);
			cc = this.creditCardService.save(cc);
			this.creditCardService.flush();
			this.creditCardService.delete(cc);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDisplaying(final String username, final String creditCardId, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);
			final CreditCard cc = this.creditCardService.findOne(this.extract(creditCardId));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
