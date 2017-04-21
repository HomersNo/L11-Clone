
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "relationshipType"), @Index(columnList = "birthDate"), @Index(columnList = "genre"), @Index(columnList = "banned"), @Index(columnList = "country"), @Index(columnList = "description"), @Index(columnList = "state"),
	@Index(columnList = "province"), @Index(columnList = "city")
})
public class CreditHolder extends Actor {

	// Constructors -----------------------------------------------------------

	public CreditHolder() {
		super();
	}


	// Attributes -------------------------------------------------------------
	private Double	cumulatedFee;


	public Double getCumulatedFee() {
		return this.cumulatedFee;
	}
	public void setCumulatedFee(final Double cumulatedFee) {
		this.cumulatedFee = cumulatedFee;
	}

	// Relationships ----------------------------------------------------------

}
