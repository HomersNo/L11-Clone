
package forms;

import java.util.Collection;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.Chorbi;
import domain.Urrl;

public class ChirpAttach {

	public ChirpAttach() {
		super();
	}


	private String				subject;
	private String				text;
	private Chorbi				recipient;
	private String				attachment;
	private Collection<Urrl>	attachments;


	@URL
	@NotBlank
	public String getAttachment() {

		return this.attachment;
	}
	public void setAttachment(final String attachment) {

		this.attachment = attachment;
	}
	public String getText() {

		return this.text;
	}
	public void setText(final String text) {

		this.text = text;
	}

	public String getSubject() {

		return this.subject;
	}
	public void setSubject(final String subject) {

		this.subject = subject;
	}

	public Collection<Urrl> getAttachments() {

		return this.attachments;
	}
	public void setAttachments(final Collection<Urrl> attachments) {

		this.attachments = attachments;
	}

	public Chorbi getRecipient() {

		return this.recipient;
	}
	public void setRecipient(final Chorbi recipient) {

		this.recipient = recipient;
	}

}
