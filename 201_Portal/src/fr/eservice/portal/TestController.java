package fr.eservice.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@Autowired
	Person myself;

	@RequestMapping("/helloworld")
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello world !");
		return "helloWorld";
	}
	
	@RequestMapping( value= "/form", method=RequestMethod.GET )
	public String form(Model model) {
		Person p;
		if ( myself == null ) {
			p = new Person();
			p.name = "Guillaume";
			p.gender = "M";
		} else {
			p = myself;
		}
		model.addAttribute("person", p);
		
		return "form";
	}
	
	@RequestMapping( value= "/form", method=RequestMethod.POST )
	@ResponseBody
	public String handleForm(Person person) {
		myself = person;
		return "name = " + person.name + "\n"
				+ "gender = " + person.gender;
	}
}
