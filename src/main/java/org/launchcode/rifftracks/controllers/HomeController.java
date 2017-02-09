package org.launchcode.rifftracks.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.rifftracks.models.Song;
import org.launchcode.rifftracks.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends AbstractController{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	//separate home page for verified users
	
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request, Model model){
		List<Song> originals = songDao.findAll();
		HttpSession thisSession = request.getSession();
		User author = getUserFromSession(thisSession);
		String username = author.getUsername();
		
		model.addAttribute("songs", originals);
		model.addAttribute("username", username);
		return "home";
	}
}