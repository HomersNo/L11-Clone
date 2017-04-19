
package controllers.administrator;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.SystemConfigurationService;
import controllers.AbstractController;
import domain.Chorbi;
import domain.SystemConfiguration;

@Controller
@RequestMapping("/systemConfiguration/administrator")
public class SystemConfigurationAdministratorController extends AbstractController {

	//Services

	@Autowired
	private SystemConfigurationService	scService;

	@Autowired
	private ChorbiService				chorbiService;


	//Contructor

	public SystemConfigurationAdministratorController() {
		super();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		SystemConfiguration system;

		system = this.scService.findMain();
		result = this.createEditModelAndView(system);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SystemConfiguration system, final BindingResult binding) {
		ModelAndView result;
		final SystemConfiguration sc;
		if (binding.hasErrors())
			result = this.createEditModelAndView(system);
		else
			try {
				sc = this.scService.save(system);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(system, "system.commit.error");
			}
		return result;
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;

		result = new ModelAndView("systemConfiguration/dashboard");

		final List<Object[]> cities = this.chorbiService.chorbiesPerCity();
		final List<Object[]> countries = this.chorbiService.chorbiesPerCountry();
		final Double avgAgePerActor = this.chorbiService.findAvgChorbiesAge();
		final Integer[] minMaxAge = this.chorbiService.findMinMaxChorbiesAge();
		final Double ratioCreditCard = this.scService.findRatioChorbiesWithoutCreditCard();
		final Double ratioLove = this.chorbiService.ratioChorbiLove();
		final Double ratioFriendship = this.chorbiService.ratioChorbiFriendship();
		final Double ratioActivities = this.chorbiService.ratioChorbiActivities();
		final Collection<Chorbi> chorbiesOrderLikes = this.chorbiService.findChorbiesOrderByLikes();
		final Double avgLikesPerActor = this.scService.averageLikesPerChorbi();
		final Long[] minMaxLikes = this.scService.minMaxLikesPerChorbi();
		final Double minSentMessagesPerActor = this.scService.averageChirpsFromChorbi();
		final Long[] minMaxSent = this.scService.minMaxChirpsFromChorbi();
		final Double avgReceivedMessagesPerActor = this.scService.averageChirpsToChorbi();
		final Long[] minMaxReceived = this.scService.minMaxChirpsToChorbi();
		final Collection<Chorbi> actorWithMoreSentMessages = this.chorbiService.findChorbiesMoreChirpsSent();
		final Collection<Chorbi> actorWithMoreReceivedMessages = this.chorbiService.findChorbiesMoreChirpsReceived();

		result.addObject("cities", cities);
		result.addObject("countries", countries);
		result.addObject("minAgePerActor", minMaxAge[0]);
		result.addObject("avgAgePerActor", avgAgePerActor);
		result.addObject("maxAgePerActor", minMaxAge[1]);
		result.addObject("ratioCreditCard", ratioCreditCard);
		result.addObject("ratioLove", ratioLove);
		result.addObject("ratioFriendship", ratioFriendship);
		result.addObject("ratioActivities", ratioActivities);
		result.addObject("chorbiesOrderLikes", chorbiesOrderLikes);
		result.addObject("minLikesPerActor", minMaxLikes[0]);
		result.addObject("avgLikesPerActor", avgLikesPerActor);
		result.addObject("maxLikesPerActor", minMaxLikes[1]);
		result.addObject("minSentMessagesPerActor", minMaxSent[0]);
		result.addObject("avgSentMessagesPerActor", minSentMessagesPerActor);
		result.addObject("maxSentMessagesPerActor", minMaxSent[1]);
		result.addObject("minReceivedMessagesPerActor", minMaxReceived[0]);
		result.addObject("avgReceivedMessagesPerActor", avgReceivedMessagesPerActor);
		result.addObject("maxReceivedMessagesPerActor", minMaxReceived[1]);
		result.addObject("actorWithMoreSentMessages", actorWithMoreSentMessages);
		result.addObject("actorWithMoreReceivedMessages", actorWithMoreReceivedMessages);
		result.addObject("requestURI", "systemConfiguration/dashboard.do");

		return result;
	}

	// Ancillary methods

	protected ModelAndView createEditModelAndView(final SystemConfiguration system) {
		ModelAndView result;

		result = this.createEditModelAndView(system, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final SystemConfiguration system, final String message) {
		ModelAndView result;

		final String requestURI = "systemConfiguration/administrator/edit.do";

		result = new ModelAndView("systemConfiguration/edit");
		result.addObject("system", system);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
