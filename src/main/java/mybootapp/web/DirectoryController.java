package mybootapp.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
	@RequestMapping("")
	public ModelAndView index(HttpSession session) {
		return new ModelAndView("index");
	}
	
	@RequestMapping("profiles")
	public ModelAndView showProfiles(HttpSession session, @RequestParam(required = false) Optional<Integer> id, @RequestParam(required = false) Optional<Integer> page) {
		User user = getUser(session);
		
		if(id.isPresent()) {
			Person person = manager.findPerson(user, id.get());
			System.err.println("PROFILE "+person.getBirthdate()+person.getEmail());
			return new ModelAndView("person", "person", person);
		} else {
			int part = 0;
			int pageSize = 10;
			if(page.isPresent()) {
				part = page.get();
				if(part < 0) part = 0; 
			}
			
			Collection<Group> groups = manager.findAllGroup(user);
			ArrayList<Person> persons = new ArrayList<Person>();
			
			groups.forEach(group -> {
				group.getPersons().forEach(person -> {
					persons.add(person);
				});
			});
			
			ArrayList<Person> filteredPersons = new ArrayList<Person>();
			
			for(int i = part*pageSize; i < part*pageSize+pageSize && i < persons.size(); i++) {
				filteredPersons.add((Person) persons.toArray()[i]);
			}
					
			System.err.println("GENERATED "+filteredPersons.size());
			return new ModelAndView("personList", "persons", filteredPersons);
		}
	}
	
	@RequestMapping("profiles/find")
	public ModelAndView showProfilesFind(HttpSession session, String name) {
		User user = getUser(session);
		final var result = manager.findPersonsByName(user, name);
		return new ModelAndView("personList", "persons", result);
		/*if(user.GetIsLogged()) {
			
		}else {
			
		}*/
		//return null;
	}
	
	@RequestMapping("groups")
	public ModelAndView showGroups(HttpSession session, @RequestParam(required = false) Optional<Integer> id, @RequestParam(required = false) Optional<Integer> page) {
		User user = getUser(session);
		
		if(id.isPresent()) {
			Group group = manager.findGroup(user, id.get());
			return new ModelAndView("group", "group", group);
		} else {
			int part = 0;
			int pageSize = 10;
			if(page.isPresent()) {
				part = page.get();
				if(part < 0) part = 0; 
			}
			
			Collection<Group> groups = manager.findAllGroup(user);
			ArrayList<Group> filteredGroups = new ArrayList<Group>();
			
			for(int i = part*pageSize; i < part*pageSize+pageSize && i < groups.size(); i++) {
				filteredGroups.add((Group) groups.toArray()[i]);
			}
					
			System.err.println("GENERATED "+filteredGroups.size());
			return new ModelAndView("groupList", "groups", filteredGroups);
		}
	}
	
	@RequestMapping("/groups/find")
	public ModelAndView showGroupsFind(HttpSession session, String name) {
		User user = getUser(session);
		final var result = manager.findGroupsByName(user, name);
		return new ModelAndView("groupList", "groups", result);
	}
	
	@RequestMapping(value = "log", method = RequestMethod.GET)
	public ModelAndView loginOrOut(HttpSession session) {
		User user = getUser(session);
		if(user.GetIsLogged()) user.setIsLogged(false);
		
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "log", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpSession session, @RequestParam("email")String email, @RequestParam("password")String password) {
		User user = getUser(session);
		
		//FIND USER BY EMAIL
		long userID;
	
		if(manager.login(user, 0, password)) {
			return new ModelAndView("index");
		} else {
			return new ModelAndView("login");
		}
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
