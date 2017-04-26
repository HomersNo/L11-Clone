package controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.CreditCard;
import domain.Manager;
import forms.RegisterManager;
import services.CreditCardService;
import services.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	//Services

		@Autowired
		private ManagerService	managerService;

		@Autowired
		private CreditCardService	creditCardService;

		//Constructor

		public ManagerController() {
			super();
		}

		//Register
		
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView register() {
			ModelAndView result;
			RegisterManager registerManager;

			registerManager = new RegisterManager();
			result = createEditModelAndView(registerManager);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid RegisterManager registerManager, BindingResult binding) {
			ModelAndView result;
			ArrayList<Object> aux;
			Manager manager;
			CreditCard creditCard;

			aux = managerService.reconstruct(registerManager, binding);
			manager = (Manager) aux.get(0);
			creditCard = (CreditCard) aux.get(1);
			if (binding.hasErrors()) {
				result = createEditModelAndView(registerManager);
			} else {
				try {
					manager = managerService.register(manager);
					creditCard.setHolder(manager);
					creditCard = creditCardService.save(creditCard);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (Throwable oops) {
					registerManager.setAccept(false);
					result = createEditModelAndView(registerManager, "manager.commit.error");
				}
			}
			return result;
		}
		
		// Ancillary methods
		
		protected ModelAndView createEditModelAndView(RegisterManager registerManager) {
			ModelAndView result;

			result = createEditModelAndView(registerManager, null);

			return result;
		}
		protected ModelAndView createEditModelAndView(RegisterManager registerManager, String message) {
			ModelAndView result;

			String requestURI = "manager/edit.do";

			result = new ModelAndView("manager/register");
			result.addObject("registerManager", registerManager);
			result.addObject("message", message);
			result.addObject("requestURI", requestURI);

			return result;
		}
	
}
