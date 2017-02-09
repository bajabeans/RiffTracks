package org.launchcode.rifftracks.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "song")
public class Song extends AbstractEntity{
	
	private String title;
	private String notes;
	private String genre;
	private String dlurl;
	private String previewurl;
	private Date created;
	private User author;
	private int version;
	private int songnum;
	private String childid;
	private boolean guitar;
	private boolean bass;
	private boolean vocals;
	private boolean drums;
	private boolean piano;
	private boolean instruments;
	
	//, boolean bass, boolean vocals, boolean drums, boolean piano, boolean instruments
	
	//All listings of versions of Song
	private List <Version> versions;
	
	public Song(){}
	
	public Song(String title, String notes, String genre, String dlurl, 
			String previewurl, User author, int version, int songnum, String childid,
			boolean guitar, boolean piano, boolean drums, boolean bass, boolean vocals, boolean instruments){
		
		super();
		
		this.title = title;
		this.notes = notes;
		this.genre = genre;
		this.dlurl = dlurl;
		this.previewurl = previewurl;
		this.author = author;
		this.created = new Date();
		this.version = version;
		this.songnum = songnum;
		this.childid = childid;
		this.guitar = guitar;
		this.bass = bass;
		this.vocals = vocals;
		this.drums = drums;
		this.piano = piano;
		this.instruments = instruments;
		
		author.addSong(this);
	}
	@NotNull
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@NotNull
	@Column(name="genre")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@NotNull
	@Column(name="dlurl")
	public String getDlurl() {
		return dlurl;
	}

	public void setDlurl(String dlurl) {
		this.dlurl = dlurl;
	}

	@NotNull
	@Column(name="previewurl")
	public String getPreviewurl() {
		return previewurl;
	}

	public void setPreviewurl(String previewurl) {
		this.previewurl = previewurl;
	}

	@NotNull
	@Column(name="version")
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@NotNull
	@Column(name="songnum")
	public int getSongnum(){
		return songnum;
	}
	
	public void setSongnum(int songnum){
		this.songnum = songnum;
	}
	
	@NotNull
	@Column(name="childid")
	public String getChildid(){
		return childid;
	}
	
	public void setChildid(String childid){
		this.childid = childid;
	}
	
	@NotNull
	@OrderColumn
	@Column(name="created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	@Column(name="guitar")
	public boolean isGuitar() {
		return guitar;
	}

	public void setGuitar(boolean guitar) {
		this.guitar = guitar;
	}
	
	@Column(name="piano")
	public boolean isPiano() {
		return piano;
	}

	public void setPiano(boolean piano) {
		this.piano = piano;
	}

	@Column(name="drums")
	public boolean isDrums() {
		return drums;
	}

	public void setDrums(boolean drums) {
		this.drums = drums;
	}
	
	@Column(name="bass")
	public boolean isBass() {
		return bass;
	}

	public void setBass(boolean bass) {
		this.bass = bass;
	}

	@Column(name="vocals")
	public boolean isVocals() {
		return vocals;
	}

	public void setVocals(boolean vocals) {
		this.vocals = vocals;
	}

	@Column(name="instruments")
	public boolean isInstruments() {
		return instruments;
	}

	public void setInstruments(boolean instruments) {
		this.instruments = instruments;
	}
	

	@ManyToOne
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	@OneToMany
	@JoinColumn(name = "songid_uid")
	public List<Version> getVersions(){
		return versions;
	}
	
	public void setVersions(List<Version> versions){
		this.versions = versions;
	}
	
	protected void addVersions(Version version){
		versions.add(version);
	}
	
	
}
