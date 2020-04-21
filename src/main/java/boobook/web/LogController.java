package boobook.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boobook.business.IDirectoryManager;
import boobook.model.User;

@Controller
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	IDirectoryManager manager;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {	
		return new ModelAndView("log/login");
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpSession session, @RequestParam("email")String email, @RequestParam("password")String password) {
		User user = getUser(session);
		
		System.err.println("[CONTROLER] login e:"+email+" p:"+password);
		
		if(manager.login(user, email, password)) {
			session.setAttribute("user", user);
			user.setConnectionError(false);
			return new ModelAndView("profile/profile", "person", user.getPerson());
		}
		else {
			user.setConnectionError(true);
			return new ModelAndView("log/login");
		}
	}
	
	@RequestMapping("/out")
	public ModelAndView logout(HttpSession session) {
		User user = getUser(session);
		manager.logout(user);
		session.setAttribute("user", user);
		return new ModelAndView("log/login");
	}
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public ModelAndView forgetPassword() {
		return new ModelAndView("log/forgetPassword");
	}
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public ModelAndView doForgetPassword(@RequestParam("email") String email) {
		if(manager.resetPassword(email)) return new ModelAndView("log/forgetPasswordConfirm", "email", email);
		else return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(@RequestParam("token") String token) {
		return new ModelAndView("log/resetPassword", "token", token);
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String doResetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
		if(manager.changePassword(token, password)) return "redirect:";
		return "redirect:index";
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
    		System.err.println("[CONTROLER] get existing session | logged in:"+user.getIsLogged()+" | p:"+user.getPerson());
    	}
    	return user;
	}
}
