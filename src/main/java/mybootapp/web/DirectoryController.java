package mybootapp.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
	@ModelAttribute("groups")
    public Map<Long, String> groups() {
		Map<Long, String> groups = new LinkedHashMap<>();
		manager.findAllGroup(null).forEach(group -> {
			groups.put(group.getId(), group.getName());
		});
        return groups;
    }
	
	@RequestMapping("")
	public ModelAndView index(HttpSession session) {
		return new ModelAndView("index");
	}
	
	@RequestMapping("profiles")
	public ModelAndView showProfiles(HttpSession session, @RequestParam(required = false) Optional<Integer> id, @RequestParam(required = false) Optional<Integer> page) {
		User user = getUser(session);
		
		if(id.isPresent()) {
			Person person = manager.findPerson(user, id.get());
			System.err.println("[CONTROLER] profile infos b:"+person.getBirthdate()+" e:"+person.getEmail());
			return new ModelAndView("person", "person", person);
		} else {
			int part = 0;
			int pageSize = 10;
			if(page.isPresent()) {
				part = page.get();
				if(part < 0) part = 0; 
			}
			
			ArrayList<Person> persons = new ArrayList<Person>(manager.findAllPersons(user));			

			int firstIndex = part*pageSize;
			int lastIndex = part*pageSize+pageSize > persons.size() ? persons.size() : part*pageSize+pageSize;
			
			return new ModelAndView("personList", "persons", persons.subList(firstIndex, lastIndex));
		}
	}
	
	@RequestMapping("profiles/find")
	public ModelAndView showProfilesFind(HttpSession session, @RequestParam("name")String name) {
		User user = getUser(session);
		final var result = manager.findPersonsByName(user, name);
		return new ModelAndView("personSearch", "persons", result);
	}
	
	@RequestMapping(value = "profiles/edit", method = RequestMethod.GET)
	public ModelAndView editProfile(HttpSession session, @RequestParam int id) {
		User user = getUser(session);
		
		if(!user.GetIsLogged()) return new ModelAndView("index");
		if(user.getPerson() == null) return new ModelAndView("index");
				
		return new ModelAndView("editProfile", "person", user.getPerson());
	}
	
	@RequestMapping(value = "profiles/edit", method = RequestMethod.POST)
	public ModelAndView saveProfile(HttpSession session, @ModelAttribute @Valid Person p, BindingResult result) {
		User user = getUser(session);
		
		if(!user.GetIsLogged()) return new ModelAndView("index");
		if(user.getPerson() == null) return new ModelAndView("index");
		
		Group g = manager.findGroup(user, p.getGroup().getId());
		if(g == null) return new ModelAndView("index");
		p.setGroup(g);
		
		System.err.println("[CONTROLER] update profile p:"+p.getName()+" g:"+p.getGroup().getName());
		manager.updatePerson(user, p);
		
		return new ModelAndView("person", "person", p);
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
			
			ArrayList<Group> groups = new ArrayList<Group>(manager.findAllGroup(user));
			
			int firstIndex = part*pageSize;
			int lastIndex = part*pageSize+pageSize > groups.size() ? groups.size() : part*pageSize+pageSize;
					
			return new ModelAndView("groupList", "groups", groups.subList(firstIndex, lastIndex));
		}
	}
	
	@RequestMapping("/groups/find")
	public ModelAndView showGroupsFind(HttpSession session, @RequestParam("name")String name) {
		User user = getUser(session);
		System.err.println("[CONTROLER] group find for:"+name);
		final var result = manager.findGroupsByName(user, name);
		System.err.println("[CONTROLER] group find s:"+result.size());
		return new ModelAndView("groupSearch", "groups", result);
	}
	
	@RequestMapping(value = "log", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {	
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "log", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpSession session, @RequestParam("email")String email, @RequestParam("password")String password) {
		User user = getUser(session);
		
		System.err.println("[CONTROLER] login e:"+email+" p:"+password);
		
		if(manager.login(user, email, password)) {
			session.setAttribute("user", user);
			return new ModelAndView("index");
		}
		else {
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {
		User user = getUser(session);
		manager.logout(user);
		session.setAttribute("user", user);
		return new ModelAndView("login");
	}
		
	private User getUser(HttpSession session) {
		User user;
    	if(session.getAttribute("user") == null) {
    		System.err.println("[CONTROLER] new user session ");
    		user = new User();
    		session.setAttribute("user", user);
    	}
    	else {
    		user = (User) session.getAttribute("user");
    		System.err.println("[CONTROLER] get existing session | logged in:"+user.GetIsLogged()+" | p:"+user.getPerson());
    	}
    	return user;
	}
	
}
