
package forms;

import java.util.Collection;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.Chorbi;

public class ChirpAttach {

	public ChirpAttach() {
		super();
	}


	private String				subject;
	private String				text;
	private Chorbi				recipient;
	private String				attachment;
	private Collection<String>	attachments;


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

	public Collection<String> getAttachments() {

		return this.attachments;
	}
	public void setAttachments(final Collection<String> attachments) {

		this.attachments = attachments;
	}

	public Chorbi getRecipient() {

		return this.recipient;
	}
	public void setRecipient(final Chorbi recipient) {

		this.recipient = recipient;
	}

}
