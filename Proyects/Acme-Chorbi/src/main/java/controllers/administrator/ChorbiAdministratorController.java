
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.ChorbiService;
import domain.Chorbi;

@Controller
@RequestMapping("/chorbi/administrator")
public class ChorbiAdministratorController {

	//Services

	@Autowired
	private ChorbiService	chorbiService;


	//Constructor

	public ChorbiAdministratorController() {
		super();
	}

	//Register
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam final int chorbiId, final RedirectAttributes redir) {
		ModelAndView result;

		try {
			this.chorbiService.banChorbi(chorbiId);
			result = new ModelAndView("redirect:/chorbi/administrator/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/chorbi/administrador/list.do");
			redir.addFlashAttribute("message", "commit.error");
		}

		return result;
	}

	//Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String errorMessage) {
		ModelAndView result;

		Collection<Chorbi> chorbis;

		chorbis = this.chorbiService.findAll();

		result = new ModelAndView("chorbi/list");
		result.addObject("chorbis", chorbis);
		result.addObject("requestURI", "chorbi/administrator/list.do");

		return result;
	}

	//Sum Fees
	@RequestMapping(value = "/sumFee", method = RequestMethod.GET)
	public ModelAndView sumFee() {
		ModelAndView result;

		Collection<Chorbi> chorbis;

		chorbis = this.chorbiService.findAllNotBanned();
		for (final Chorbi c : chorbis)
			this.chorbiService.sumFee(c);
		result = new ModelAndView("chorbi/list");
		result.addObject("chorbis", chorbis);
		result.addObject("requestURI", "chorbi/administrator/list.do");

		return result;
	}

}
