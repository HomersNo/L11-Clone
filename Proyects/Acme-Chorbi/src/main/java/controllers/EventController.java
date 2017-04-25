
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EventService;
import controllers.AbstractController;
import domain.Event;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	//Services

	@Autowired
	private EventService	eventService;


	//Constructor

	public EventController() {
		super();
	}

	//Listing

	@RequestMapping(value = "/listImminent", method = RequestMethod.GET)
	public ModelAndView listImminent(@RequestParam(required = false) String errorMessage) {
		ModelAndView result;

		Collection<Event> events;

		events = eventService.findAllEventsInOneMonth();

		result = new ModelAndView("event/list");
		result.addObject("events", events);
		result.addObject("errorMessage", errorMessage);

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) String errorMessage) {
		ModelAndView result;

		Collection<Event> events;

		events = eventService.findAll();

		result = new ModelAndView("event/list");
		result.addObject("events", events);
		result.addObject("errorMessage", errorMessage);

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	//Needs further testing

}
