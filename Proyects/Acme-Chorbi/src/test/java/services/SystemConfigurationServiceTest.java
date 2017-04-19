
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.SystemConfiguration;
import domain.Urrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SystemConfigurationServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------------------
	@Autowired
	private SystemConfigurationService	sysConService;

	@Autowired
	private AdministratorService		adminService;


	// Tests ---------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Test
	public void driverModifyingCache() {

		final Collection<Urrl> banners = new ArrayList<Urrl>();
		final Urrl url = new Urrl();
		url.setLink("http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png"); //Mete las url de las imágenes
		banners.add(url);
		final Collection<Urrl> bannersEmpty = new ArrayList<Urrl>();
		final Collection<Urrl> bannersFull = new ArrayList<Urrl>();
		final Collection<Urrl> bannersWrong = new ArrayList<Urrl>();
		for (int i = 0; i < 20; i++)
			bannersFull.add(url);
		final Urrl urlWrong = new Urrl();
		urlWrong.setLink("Esto no es un link");
		bannersWrong.add(urlWrong);

		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			final Date dateWrong = sdf.parse("10:00:00");
			final Date dateRight = sdf.parse("13:00:00");

			final Object testingData[][] = {
				{	// Modificación correcta: Caché correcta.
					"admin", bannersFull, dateRight, null
				}, { // Modificacion erronea: Cache errónea.
					"admin", bannersEmpty, dateWrong, IllegalArgumentException.class
				}, { // Modificacion erronea: Banners vacíos.
					"admin", bannersEmpty, dateRight, IllegalArgumentException.class
				}, { // Modificacion erronea: Banners con formato erroneo.
					"admin", bannersWrong, dateRight, IllegalArgumentException.class
				}, { // Modificacion erronea: Banners completo.
					"admin", bannersFull, dateRight, IllegalArgumentException.class
				}
			};
			for (int i = 0; i < testingData.length; i++)
				this.templateModifyingCache((String) testingData[i][0], (Collection<Urrl>) testingData[i][1], (Date) testingData[i][2], (Class<?>) testingData[i][3]);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
	}
	// Templates ----------------------------------------------------------
	protected void templateModifyingCache(final String username, final Collection<Urrl> banners, final Date cacheTime, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.adminService.findByPrincipal();
			this.authenticate(username);
			final SystemConfiguration sc = this.sysConService.findMain();
			sc.setBanners(banners);
			sc.setCacheTime(cacheTime);
			this.sysConService.save(sc);
			this.sysConService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
