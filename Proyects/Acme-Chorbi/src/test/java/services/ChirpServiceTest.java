
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chirp;
import domain.Chorbi;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChirpServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------------------
	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ChorbiService	chorbiService;


	// Tests ---------------------------------------------------------------
	//An actor who is authenticated must be able to:
	//	o Exchange chirps with other actors.
	//	o Erase his or her chirps, which requires previous confirmation
	@SuppressWarnings("unchecked")
	@Test
	public void driverCreation() {
		final Collection<String> attachments = new ArrayList<String>();
		final String url = "http://www.bouncepen.com/wp-content/themes/twentyfifteen/uploads/user-photo/dummy-image.png";
		attachments.add(url);
		final Collection<String> attachmentsEmpty = new ArrayList<String>();
		final Collection<String> attachmentsFull = new ArrayList<String>();
		final Collection<String> attachmentWrong = new ArrayList<String>();
		for (int i = 0; i < 20; i++)
			attachmentsFull.add(url);
		final String urlWrong = "Esto no es un link";
		attachmentWrong.add(urlWrong);

		final Object testingData[][] = {
			{		// Creación correcta de un Chirp: Un adjunto
				"chorbi1", "correcto", "correcto", attachments, null
			}, {		// Creación correcta de un Chirp: Sin adjuntos;
				"chorbi1", "correcto", "correcto", attachmentsEmpty, null
			}, {		// Creación correcta de un Chirp: Muchos adjuntos;
				"chorbi1", "correcto", "correcto", attachmentsFull, null
			}, {	// Creación incorrecta de un Chirp: Admin intenta enviar un chirp, pero no tiene carpetas
				"admin", "incorrecto", "incorrecto", attachments, NullPointerException.class
			}, {	// Creación errónea de un Chirp: title vacío.
				"chorbi1", "", "incorrecto", attachments, ConstraintViolationException.class
			}, {	// Creación errónea de un Chirp: title nulo.
				"chorbi1", null, "incorrecto", attachments, ConstraintViolationException.class
			}, {	// Creación errónea de un Chirp: text vacío.
				"chorbi1", "incorrecto", "", attachments, ConstraintViolationException.class
			}, {	// Creación errónea de un Chirp: text nulo.
				"chorbi1", "incorrecto", null, attachments, ConstraintViolationException.class
			}, {	// Creación errónea de un Chirp: attachment nulo
				"chorbi1", "incorrecto", "incorrecto", null, ConstraintViolationException.class
			}, {	// Creación errónea de un Chirp: attachment Mal
				"chorbi1", "incorrecto", "incorrecto", attachmentWrong, ConstraintViolationException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreation((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (Class<?>) testingData[i][4]);
	}
	//- An actor who is authenticated must be able to:
	//	o List the chirps that he or she's got and reply to them.
	//	o List the chirps that he or she's got and forward them
	@Test
	public void driverFindAllByFolderId() {
		final Object testingData[][] = {
			{
				"chorbi1", "Received", null
			}, {
				"chorbi1", "Sent", null
			}, {
				"chorbi2", "Received", null
			}, {
				"chorbi2", "Sent", null
			}, {
				"chorbi3", "Received", null
			}, {
				"chorbi3", "Sent", null
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateFindAllByFolderId((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	// Templates ----------------------------------------------------------
	protected void templateCreation(final String username, final String subject, final String text, final Collection<String> attachment, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);
			final Chorbi cus = this.chorbiService.findOne(this.extract("chorbi2"));
			final Chirp m = this.chirpService.create();
			m.setAttachments(attachment);
			m.setText(text);
			m.setSubject(subject);
			m.setRecipient(cus);

			this.chirpService.send(m);

			final Chorbi sender = this.chorbiService.findByPrincipal();
			final Chorbi recipient = (Chorbi) m.getRecipient();

			Assert.isTrue(m.getSender().equals(sender) && m.getRecipient().equals(recipient));

			final Folder recipientFolder = this.folderService.findSystemFolder(recipient, "Received");
			final Folder senderFolder = this.folderService.findSystemFolder(sender, "Sent");

			final Collection<Chirp> recipientChirps = this.chirpService.findAllByFolderWithNoCheck(recipientFolder.getId());
			final Collection<Chirp> senderChirps = this.chirpService.findAllByFolderWithNoCheck(senderFolder.getId());

			for (final Chirp r : recipientChirps)
				for (final Chirp s : senderChirps)
					if (r.getSubject() == s.getSubject() && r.getMoment().equals(s.getMoment()) && r.getAttachments() == s.getAttachments() && r.getRecipient().equals(s.getRecipient()) && r.getText() == s.getText())
						Assert.isTrue(r.getSubject() == s.getSubject() && r.getMoment().equals(s.getMoment()) && r.getAttachments() == s.getAttachments() && r.getRecipient().equals(s.getRecipient()) && r.getText() == s.getText());
			this.chirpService.delete(m);

			final Collection<Chirp> all = this.chirpService.findAll();
			Assert.isTrue(!(all.contains(m)));

			this.chirpService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateFindAllByFolderId(final String username, final String folderName, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(username);
			final Chorbi actor = this.chorbiService.findByPrincipal();
			final Folder folder = this.folderService.findSystemFolder(actor, folderName);
			this.chirpService.findAllByFolder(folder.getId());
			this.unauthenticate();

			this.chirpService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
