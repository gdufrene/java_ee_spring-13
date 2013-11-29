package fr.eservice.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/*
 * Le scope permet de controller la durée de vie 
 * du controller et donc des attributs qui y sont injectés (autowired)
 */
@Scope("session")
public class TestController {
	
	
	/*
	 * Un objet Person va être créé et injecté par
	 * spring, en suivant le pattern Singleton par défaut
	 */
	@Autowired
	Person myself;
	

	/*
	 * Traite la requête associé à l'url /helloworld
	 * Ajoute un attribut message dans le model qui pourra
	 * être utilisé dans la vue.
	 * 
	 * Retourne une chaine de caractère qui permettra de déterminer
	 * quelle jsp doit être appelé gràce au UrlBasedViewResolver
	 * configuré dans le contexte spring.
	 */
	@RequestMapping("/helloworld")
	public String helloWorld(Model model, @RequestParam(value = "name", defaultValue = "world") String name) {
		/*
		// Autre solution possible : ajouter le HTTPServletRequest en paramètre
		// et l'utiliser dans l'opération...
		// inconvéniant : votre controller devient dépendant de l'api servlet,
		//                ce n'est pas idéal pour les tests ou la réutilisation.
		String name = req.getParameter("name");
		if ( name == null || name.isEmpty() ) {
			name = "world";
		}
		*/
		model.addAttribute("message", "Hello " + name + " !");
		return "helloWorld";
	}
	
	
	/*
	 * Gestion de formulaire simplifiée avec spring.
	 * 
	 * On se branche à la méthode GET de l'url /form
	 * On affecte l'objet Person injecté par spring dans le model
	 * et on affiche le formualire ...
	 */
	@RequestMapping( value= "/form", method=RequestMethod.GET )
	public String form(Model model) {
		model.addAttribute("person", myself);
		return "form";
	}
	
	
	/*
	 * On peut traiter le POST du formulaire dans une autre méthode.
	 * Spring se charge de transpormer les paramètre de la requête
	 * HTTP en un objet Person dont les attributs sont public
	 * ou possède un accesseur (get/set)
	 * 
	 * l'annotation @ResponseBody indique à spring que l'on souhaite
	 * rendre le résultat de cette méthode directement vers le 
	 * la réponse HTTP, sans passer par une vue.
	 */
	@RequestMapping( value= "/form", method=RequestMethod.POST )
	@ResponseBody
	public String handleForm(Person person) {
		myself = person;
		return "name = " + person.name + "\n"
				+ "gender = " + person.gender;
	}
}
