package org.launchcode.rifftracks.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.rifftracks.models.User;
import org.launchcode.rifftracks.models.dao.SongDao;
import org.launchcode.rifftracks.models.dao.UserDao;
import org.launchcode.rifftracks.models.dao.VersionDao;
import org.springframework.beans.factory.annotation.Autowired;



public abstract class AbstractController {
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected SongDao songDao;
	
	@Autowired
	protected VersionDao versionDao;
	
	public static final String userSessionKey = "user_id";
	
    protected User getUserFromSession(HttpSession session) {
    	
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findByUid(userId);
    }
    
    protected void setUserInSession(HttpSession session, User user) {
    	session.setAttribute(userSessionKey, user.getUid());
    }

}