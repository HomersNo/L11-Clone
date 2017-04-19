package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Chorbi;
import forms.RegisterChorbi;
import services.ChorbiService;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController {

	//Services

		@Autowired
		private ChorbiService	chorbiService;


		//Constructor

		public ChorbiController() {
			super();
		}

		//Register
		
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView register() {
			ModelAndView result;
			RegisterChorbi registerChorbi;

			registerChorbi = new RegisterChorbi();
			result = createEditModelAndView(registerChorbi);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid RegisterChorbi registerChorbi, BindingResult binding) {
			ModelAndView result;
			Chorbi chorbi;

			chorbi = chorbiService.reconstruct(registerChorbi, binding);
			if (binding.hasErrors()) {
				result = createEditModelAndView(registerChorbi);
			} else {
				try {
					chorbi = chorbiService.register(chorbi);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (Throwable oops) {
					registerChorbi.setAccept(false);
					result = createEditModelAndView(registerChorbi, "chorbi.commit.error");
				}
			}
			return result;
		}
		
		// Ancillary methods
		
		protected ModelAndView createEditModelAndView(RegisterChorbi registerChorbi) {
			ModelAndView result;

			result = createEditModelAndView(registerChorbi, null);

			return result;
		}
		protected ModelAndView createEditModelAndView(RegisterChorbi registerChorbi, String message) {
			ModelAndView result;

			String requestURI = "chorbi/edit.do";

			result = new ModelAndView("chorbi/register");
			result.addObject("registerChorbi", registerChorbi);
			result.addObject("message", message);
			result.addObject("requestURI", requestURI);

			return result;
		}
	
}
