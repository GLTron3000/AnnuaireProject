package mybootapp.web;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mybootapp.business.IDirectoryManager;
import mybootapp.model.Group;
import mybootapp.model.Person;
import mybootapp.model.User;

@Controller
@RequestMapping("/")
public class DirectoryController {
	
	@Autowired
	IDirectoryManager manager;
	
	@PostConstruct
	public void init() {
    	Group s = new Group("Sorceleur");
    	manager.savePerson(null, new Person(
				"Geralt", 
				"De Riv",
				"geralt@sorceleurs.org", 
				"boucherDeBlaviken.com", 
				new Date(733699763),
				"1234",
				s));
    	/*manager.savePerson(null, new Person(
				"Vesemir", 
				"REDACTED",
				"vesemir@sorceleurs.org", 
				"blogDesAnciens.com", 
				new Date(733699763),
				"12345",
				s));
    	manager.savePerson(null, new Person(
				"Cirilla Fiona Elen", 
				"Riannon",
				"ciri@sorceleurs.org", 
				"lionceauDeCintra.com", 
				new Date(733699763),
				"1234",
				s));*/
		Group m = new Group("Mage");
		manager.savePerson(null, new Person(
				"Triss", 
				"Merigold",
				"triss.merigold@mages.org", 
				"conseillerDuRoiFoltest.com", 
				new Date(733699763),
				"1234",
				m));
		/*
				manager.savePerson(null, new Person(
				"Yennefer", 
				"de Vengerberg",
				"Yennefer.De.Vengerberg@mages.org", 
				"jaimePasLaCouleur.com", 
				new Date(733699763),
				"1234",
				m));
		*/
		Group b = new Group("Barde");
		manager.savePerson(null, new Person(
				"Jaskier", 
				"Zamachowski",
				"jaskier@bardes.org", 
				"leMeilleurDesBardes.com", 
				new Date(733699763),
				"1234",
				b));
    }
	
	@RequestMapping("")
	public ModelAndView index(HttpSession session) {
		return new ModelAndView("index");
	}
	
	@RequestMapping("profiles")
	public ModelAndView showProfiles(HttpSession session, @RequestParam(required = false) Optional<Integer> id) {
		User user = getUser(session);
		
		if(id.isPresent()) {
			Person person = manager.findPerson(user, id.get());
			return new ModelAndView("person", "person", person);
		} else {
			Collection<Group> groups = manager.findAllGroup(user);
			return new ModelAndView("personList", "groups", groups);
		}
	}
	
	@RequestMapping("groups")
	public ModelAndView showGroups(HttpSession session, @RequestParam(required = false) Optional<Integer> id) {
		User user = getUser(session);
		
		if(id.isPresent()) {
			Group group = manager.findGroup(user, id.get());
			return new ModelAndView("group", "group", group);
		} else {
			Collection<Group> groups = manager.findAllGroup(user);
			return new ModelAndView("groupList", "groups", groups);
		}
	}
	
	@RequestMapping("login")
	public ModelAndView login(HttpSession session) {
		User user = getUser(session);

		return new ModelAndView("login");
	}
	
	private User getUser(HttpSession session) {
		User user;
    	if(session.getAttribute("user") == null) {
    		user = new User();
    		session.setAttribute("user", user);
    	}
    	else user = (User) session.getAttribute("user");
    	return user;
	}
	
}
