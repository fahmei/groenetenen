package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.FiliaalService;

@Controller
@RequestMapping("/filialen")
class FiliaalControler {

	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final Logger logger = Logger.getLogger(FiliaalControler.class.getName());
	
	private final FiliaalService filiaalService;
	
	@Autowired
	FiliaalControler(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll(){
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll());
	}
	
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	String createForm(){
		return TOEVOEGEN_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	String create(){
		logger.info("filiaal toevoegen aan database");
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
	
}
