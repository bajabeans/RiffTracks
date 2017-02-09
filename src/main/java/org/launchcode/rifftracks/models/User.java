package org.launchcode.rifftracks.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {
	
	private String username;
	private String pwHash;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	//All listings by user
	private List<Song> songs;
	
	private List<Version> versions;
	
	//no-arg constructor for Hibernate
	
	public User(){}
	
	public User(String username, String password, String email){
		
		super();
		
		if(!isValidUsername(username)){
			throw new IllegalArgumentException("Invalid username");
		}
		
		this.username = username;
		this.pwHash = hashPassword(password);
	}
	
	@NotNull
	@Column(name = "pwhash")
	public String getPwHash(){
		return pwHash;
	}
	
	@SuppressWarnings("unused")
	private void setPwHash(String pwHash){
		this.pwHash = pwHash;
	}
	
	@NotNull
	@Column(name = "username", unique = true)
	public String getUsername(){
		return username;
	}
	
	@SuppressWarnings("unused")
	private void setUsername(String username){
		this.username = username;
	}
	/*
	@NotNull
	@Column(name = "email", unique = true)
	public String getEmail(){
		return email;
	}
	
	@SuppressWarnings("unused")
	private void setEmail(String email){
		this.email = email;
	}
	*/
	
	@OneToMany
	@JoinColumn(name = "vauthor_uid")
	public List<Version> getVersions(){
		return versions;
	}
	
	public void setVersions(List<Version> versions){
		this.versions = versions;
	}
	
	@OneToMany
	@JoinColumn(name = "author_uid")
	public List<Song> getSongs(){
		return songs;
	}
	
	public void setSongs(List<Song> songs){
		this.songs = songs;
	}
	
	private static String hashPassword(String password){
		return encoder.encode(password);
	}
	
	public boolean isMatchingPassword(String password){
		return encoder.matches(password, pwHash);
	}
	
	public static boolean isValidPassword(String password) {
		Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
		Matcher matcher = validUsernamePattern.matcher(password);
		return matcher.matches();
	}
	
	public static boolean isValidUsername(String username) {
		Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,11}");
		Matcher matcher = validUsernamePattern.matcher(username);
		return matcher.matches();
	}
	
	public static boolean isValidEmail(String email){
		Pattern validEmailPattern = Pattern.compile("^(.+)@([^@]+[^.])$");
		Matcher matcher = validEmailPattern.matcher(email);
		return matcher.matches();
	}
	
	protected void addSong(Song song){
		songs.add(song);
	}

	protected void addVersion (Version version){
		versions.add(version);
	}

}
