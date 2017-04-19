
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SearchTemplateRepository;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;
import domain.CreditCard;
import domain.SearchTemplate;
import domain.SystemConfiguration;

@Service
@Transactional
public class SearchTemplateService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SearchTemplateRepository	searchTemplateRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CreditCardService			creditCardService;

	@Autowired
	private ChorbiService				chorbiService;

	@Autowired
	private SystemConfigurationService	scService;

	@Autowired
	private Validator					validator;


	// Constructors -----------------------------------------------------------

	public SearchTemplateService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SearchTemplate create() {

		SearchTemplate created;
		final Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		created = new SearchTemplate();
		final Chorbi principal = this.chorbiService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getId() != 0);
		created.setChorbi(principal);

		Date now;
		now = new Date(System.currentTimeMillis() - 1);
		created.setMoment(now);
		created.setChorbies(chorbies);

		return created;
	}

	public Collection<SearchTemplate> findAll() {
		Collection<SearchTemplate> result;

		result = this.searchTemplateRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SearchTemplate findOne(final int searchTemplateId) {
		Assert.isTrue(searchTemplateId != 0);

		SearchTemplate result;

		result = this.searchTemplateRepository.findOne(searchTemplateId);
		Assert.notNull(result);

		return result;
	}

	public SearchTemplate save(final SearchTemplate searchTemplate) {

		SearchTemplate saved;
		Assert.notNull(searchTemplate);
		Assert.isTrue(this.checkPrincipal(searchTemplate));
		final CreditCard creditCard = this.creditCardService.findByPrincipal();
		Assert.notNull(creditCard);
		Assert.isTrue(this.creditCardService.checkCCNumber(creditCard.getCreditCardNumber()) && this.creditCardService.expirationDate(creditCard));
		Collection<Chorbi> filtered;
		filtered = new ArrayList<Chorbi>();
		filtered.addAll(this.chorbiService.findAllNotBanned());

		if (searchTemplate.getRelationshipType() != "" && searchTemplate.getRelationshipType() != null)
			filtered.retainAll(this.chorbiService.findByRelationshipType(searchTemplate.getRelationshipType()));

		if (searchTemplate.getAge() != null)
			filtered.retainAll(this.chorbiService.findByAge(searchTemplate.getAge()));

		if (searchTemplate.getGenre() != "" && searchTemplate.getGenre() != null)
			filtered.retainAll(this.chorbiService.findByGenre(searchTemplate.getGenre()));

		if (searchTemplate.getKeyword() != "" && searchTemplate.getKeyword() != null)
			filtered.retainAll(this.chorbiService.findByKeyword(searchTemplate.getKeyword()));

		if (searchTemplate.getCountry() != "" && searchTemplate.getCountry() != null)
			filtered.retainAll(this.chorbiService.findByCountry(searchTemplate.getCountry()));

		if (searchTemplate.getState() != "" && searchTemplate.getState() != null)
			filtered.retainAll(this.chorbiService.findByState(searchTemplate.getState()));

		if (searchTemplate.getProvince() != "" && searchTemplate.getProvince() != null)
			filtered.retainAll(this.chorbiService.findByProvince(searchTemplate.getProvince()));

		if (searchTemplate.getCity() != "" && searchTemplate.getCity() != null)
			filtered.retainAll(this.chorbiService.findByCity(searchTemplate.getCity()));

		searchTemplate.setChorbies(filtered);

		final Date lastUpdate = new Date(System.currentTimeMillis() - 1);
		searchTemplate.setMoment(lastUpdate);

		saved = this.searchTemplateRepository.save(searchTemplate);
		return saved;
	}

	private boolean checkPrincipal(final SearchTemplate searchTemplate) {

		Boolean result = false;
		final UserAccount chorbi = searchTemplate.getChorbi().getUserAccount();
		final UserAccount principal = LoginService.getPrincipal();
		if (chorbi.equals(principal))
			result = true;
		return result;
	}

	/**
	 * @param searchTemplate
	 * @param binding
	 * @return
	 */
	public SearchTemplate reconstruct(final SearchTemplate searchTemplate, final BindingResult binding) {
		SearchTemplate result;

		if (searchTemplate.getId() == 0)
			result = searchTemplate;
		else {
			result = this.searchTemplateRepository.findOne(searchTemplate.getId());

			result.setChorbies(searchTemplate.getChorbies());
			result.setAge(searchTemplate.getAge());
			result.setKeyword(searchTemplate.getKeyword());
			result.setChorbi(searchTemplate.getChorbi());
			result.setCity(searchTemplate.getCity());
			result.setCountry(searchTemplate.getCountry());
			result.setGenre(searchTemplate.getGenre());
			result.setMoment(searchTemplate.getMoment());
			result.setProvince(searchTemplate.getProvince());
			result.setRelationshipType(searchTemplate.getRelationshipType());
			result.setState(searchTemplate.getState());

			this.validator.validate(result, binding);
		}

		return result;
	}

	public Boolean checkCache(final SearchTemplate searchTemplate) {

		Boolean res = true;
		final SystemConfiguration system = this.scService.findMain();
		final DateTime last = new DateTime(searchTemplate.getMoment());
		final DateTime now = DateTime.now();

		final Chorbi principal = this.chorbiService.findByPrincipal();
		final SearchTemplate chorbiTemplate = this.findSearchTemplateByChorbi(principal);
		if (chorbiTemplate != null) {
			Boolean relationshipType;
			Boolean age;
			Boolean genre;
			Boolean keyword;
			Boolean country;
			Boolean state;
			Boolean province;
			Boolean city;

			relationshipType = true;
			age = true;
			genre = true;
			keyword = true;
			country = true;
			state = true;
			province = true;
			city = true;

			if (searchTemplate.getRelationshipType() != "" && searchTemplate.getRelationshipType() != null)
				relationshipType = chorbiTemplate.getRelationshipType().equals(searchTemplate.getRelationshipType());

			if (searchTemplate.getAge() != null)
				age = chorbiTemplate.getAge().equals(searchTemplate.getAge());

			if (searchTemplate.getGenre() != "" && searchTemplate.getGenre() != null)
				genre = chorbiTemplate.getGenre().equals(searchTemplate.getGenre());

			if (searchTemplate.getKeyword() != "" && searchTemplate.getKeyword() != null)
				keyword = chorbiTemplate.getKeyword().equals(searchTemplate.getKeyword());

			if (searchTemplate.getCountry() != "" && searchTemplate.getCountry() != null)
				country = chorbiTemplate.getCountry().equals(searchTemplate.getCountry());

			if (searchTemplate.getState() != "" && searchTemplate.getState() != null)
				state = chorbiTemplate.getState().equals(searchTemplate.getState());

			if (searchTemplate.getProvince() != "" && searchTemplate.getProvince() != null)
				province = chorbiTemplate.getProvince().equals(searchTemplate.getProvince());

			if (searchTemplate.getCity() != "" && searchTemplate.getCity() != null)
				city = chorbiTemplate.getCity().equals(searchTemplate.getCity());

			final Boolean isEqual = relationshipType && age && genre && keyword && country && state && province && city;

			if (now.minus(system.getCacheTime().getTime()).isBefore(last) && isEqual)
				res = true;
			else
				res = false;
		} else
			res = false;

		return res;
	}

	public SearchTemplate findSearchTemplateByChorbi(final Chorbi principal) {
		final SearchTemplate result = this.searchTemplateRepository.findByChorbiId(principal.getId());
		return result;
	}

	public void flush() {
		this.searchTemplateRepository.flush();
	}

	// Other business methods -------------------------------------------------

}
