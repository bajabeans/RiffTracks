package org.launchcode.rifftracks.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.rifftracks.models.Song;
import org.launchcode.rifftracks.models.User;
import org.launchcode.rifftracks.models.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SongController extends AbstractController{

	@RequestMapping(value = "/home/newsong", method = RequestMethod.GET)
	public String newSongPost(){
		return "newsong";
	}
	
	@RequestMapping(value = "/home/newsong", method = RequestMethod.POST)
	public String newSong(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile[] files){
		
		String title = request.getParameter("title");
		String notes = request.getParameter("notes");
		String genre = request.getParameter("genre");
		//String dlurl = request.getParameter("dlurl");
		//String previewurl = request.getParameter("previewurl");

		boolean guitar = request.getParameter("guitar") !=null;
		boolean piano = request.getParameter("piano") !=null;
		boolean drums = request.getParameter("drums") !=null;
		boolean bass = request.getParameter("bass") !=null;
		boolean vocals = request.getParameter("vocals") !=null;
		boolean instruments = request.getParameter("instruments") !=null;
		HttpSession thisSession = request.getSession();
		User author = getUserFromSession(thisSession);
		int version = 1;
		int songnum = 0;
		String childid = "";
		
		//file save -- creates a copy of files to local 'server' folder.  
		//Access location saved to DB 
		
		String fileName= null;
		List<String> fileLocations = new ArrayList<String>();
		
		if(files !=null && files.length > 0){
			for(int i = 0; i < files.length; i++){
				try{
					fileName = files[i].getOriginalFilename();
					byte[] bytes = files[i].getBytes();
					BufferedOutputStream buffStream = 
							new BufferedOutputStream(new FileOutputStream(new File("C:/Users/Che/testFolder/" + fileName)));
					buffStream.write(bytes);
					buffStream.close();
					
					fileLocations.add("http://127.0.0.1:8887/" + fileName);
				} catch (Exception e){
					return "You failed to upload " + fileName + ": " + e.getMessage() + "<br/>";
				}
			}
		} else {
			return "Unable to upload. File is empty.";
		}
		
		String previewurl = fileLocations.get(0);
		String dlurl = fileLocations.get(1);
		
		//need proper parameters
		if(!title.equals("")){
			Song newSong = new Song(title, notes, genre, dlurl, previewurl, author, version, songnum, 
					childid, guitar, piano, drums, bass, vocals, instruments);
			songDao.save(newSong);
			
			int uid = newSong.getUid();
			newSong.setSongnum(uid);
			String newchildid = Integer.toString(uid);
			newSong.setChildid(newchildid);
			songDao.save(newSong);
			
			//redirect to new song post
			return "redirect:/home/" + uid;
		}	
			
		
		return "newsong";
	}
	
	@RequestMapping(value = "/home/{songnum}/{version}/{childid}/newversion", method = RequestMethod.GET)
	public String versionPage(@PathVariable int songnum, @PathVariable String childid, @PathVariable int version, Model model){
		return "newversion";
	}
	
	@RequestMapping(value = "/home/{songnum}/{version}/{childid}/newversion", method = RequestMethod.POST)
	public String newVersion(@PathVariable int songnum, @PathVariable String childid, @PathVariable int version, HttpServletRequest request, Model model){
		
		String title = request.getParameter("title");
		String notes = request.getParameter("notes");
		String genre = request.getParameter("genre");
		String dlurl = request.getParameter("dlurl");
		String previewurl = request.getParameter("previewurl");
		
		boolean guitar = request.getParameter("guitar") !=null;
		boolean piano = request.getParameter("piano") !=null;
		boolean drums = request.getParameter("drums") !=null;
		boolean bass = request.getParameter("bass") !=null;
		boolean vocals = request.getParameter("vocals") !=null;
		boolean instruments = request.getParameter("instruments") !=null;
		
		HttpSession thisSession = request.getSession();
		User vauthor = getUserFromSession(thisSession);
		String parentid = childid;
		
		int versionnum = 0;
		List<Version> childversionlist = versionDao.findByParentid(parentid);
		int childversion = childversionlist.size() + 1;
		String newChildid = childid + "." + Integer.toString(childversion);
		Song songid = songDao.findByUid(songnum);
		
		if(!title.equals("")){
			Version newVersion = new Version(title, notes, genre, dlurl, previewurl,
					vauthor, songid, newChildid, versionnum, parentid, childversion, songnum,
					guitar, piano, drums, bass, vocals, instruments);
			versionDao.save(newVersion);
			
			List<Version> versionlist = versionDao.findBySonguid(songnum);
			versionnum = versionlist.size() + 1;
			newVersion.setVersion(versionnum);
			versionDao.save(newVersion);
			
			
			return "redirect:/home" ;
			
		}
		return "newversion";
		
	}
	
	
	@RequestMapping(value = "/home/{uid}", method = RequestMethod.GET)
	public String songPage(@PathVariable int uid, Model model){
		Song parentsong = songDao.findByUid(uid);
		
		List<Version> child = versionDao.findByParentid(Integer.toString(uid));
		//List<Version> children = versionDao.findByParentid(parentid)
		model.addAttribute("parent", parentsong);
		model.addAttribute("child", child);
		
		return "sp";
	}

	/*
	 * 
	 * Implementing search by instrument function WIP
	 * 
	@RequestMapping(value = "/home/search", method = RequestMethod.GET)
	public String searchPage(){
		
		List<Song> guitar = songDao.findByGuitar(true);
		List<Song> paino = songDao.findbyPiano(true);
		List<Song> drums = songDao.findByDrums(true);
		List<Song> bass = songDao.findbyBass(true);
		
		return "searchpage";
		
	}
	*/
}

