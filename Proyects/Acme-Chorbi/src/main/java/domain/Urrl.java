
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Urrl {

	// Constructors -----------------------------------------------------------

	public Urrl() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	link;


	public String getLink() {
		return this.link;
	}
	public void setLink(final String link) {
		this.link = link;
	}

}
