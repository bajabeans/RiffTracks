package org.launchcode.rifftracks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.rifftracks.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController{

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm(){
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String sigunup(HttpServletRequest request, Model model){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		String email = request.getParameter("email");
		
		if(User.isValidUsername(username) && User.isValidPassword(password) && password.equals(verify))
		{
			User newUser = new User(username, password, email);
			userDao.save(newUser);
			
			HttpSession thisSession = request.getSession();
			setUserInSession(thisSession, newUser);
			return "redirect:/home";
		}
		if(User.isValidUsername(username) == false)
		{
			String usernameError = "Not a valid username";
			model.addAttribute("username_error", usernameError);
			return "signup";
		}	
		if(User.isValidPassword(password) == false)
		{
			String pwError = "Not a valid password";
			model.addAttribute("password_error", pwError);
			return "signup";
		}
		/*
		 * 
		if(User.isValidEmail(email) == false)
		{
			String emailError = "Not a valid email";
			model.addAttribute("email_error", emailError);
			return "signup";
		}
		*/
		if(!password.equals(verify))
		{
			String verError = "Passwords do not match";
			model.addAttribute("verify_error", verError);
			return "signup";
		}
		else
		{
			return "signup";
		}	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(){
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User un = userDao.findByUsername(username);
		if(un.isMatchingPassword(password))
		{
			HttpSession thisSession = request.getSession();
			setUserInSession(thisSession, un);
			return "redirect:home";
		}
		else
		{	
			return "signup";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
	
}

