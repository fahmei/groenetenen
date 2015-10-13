package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/filialen")
class FiliaalControler {

	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	
	@RequestMapping(method = RequestMethod.GET)
	String findAll(){
		return FILIALEN_VIEW;
	}
	
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	String createForm(){
		return TOEVOEGEN_VIEW;
	}
	
}
