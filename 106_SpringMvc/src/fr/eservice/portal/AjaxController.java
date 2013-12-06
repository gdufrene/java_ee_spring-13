package fr.eservice.portal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 * Une requête ajax est une requête HTTP comme les autres,
 * sauf qu'elle est executée par la page web, à l'intérieur
 * d'un javascript plutot qu'en cliquant sur un lien ou en
 * tapant une adresse web.
 * 
 * répondre à une requête ajax se fait de la même manière que
 * pour les autres requêtes ... sauf qu'au lieu de renvoyer
 * une page complète en html, on renverra un bout de html ou uniquement
 * des données à exploitées en javascript dans le navigateur.
 * Pour cette raison, les réponses sont souvent encodées en "JSON",
 * un format de sérialisation javascript facilement exploitable.
 *  
 * essayons de faire une salle de discussion "temps réel"
 */
@Controller
public class AjaxController {
	
	List<String> history;
	
	public AjaxController() {
		history = new ArrayList<String>();
	}

	@RequestMapping("/say")
	@ResponseBody
	public String saySomething(@RequestParam("msg") String msg) {
		history.add( msg );
		return "Ok";
	}
	
	@RequestMapping(value = "/history", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String[] getHistory( @RequestParam(value="last", defaultValue="0") int last) {
		if ( last >= history.size() ) return new String[0];
		return Arrays.copyOfRange(history.toArray( new String[0] ), last, history.size());
	}
	
	
}
