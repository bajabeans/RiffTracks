package org.launchcode.rifftracks.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "version")
public class Version extends AbstractEntity{

	private String title;
	private String notes;
	private String genre;
	private String dlurl;
	private String previewurl;
	private Date created;
	private User vauthor;
	private Song songid;
	private String childid;
	private int version;
	private String parentid;
	private int childversion;
	private int songuid;
	
	private boolean guitar;
	private boolean bass;
	private boolean vocals;
	private boolean drums;
	private boolean piano;
	private boolean instruments;
	
	public Version(){}
	
	public Version(String title, String notes, String genre, String dlurl, String previewurl, 
			User vauthor, Song songid, String childid, int version, String parentid, int childversion, int songuid,
			boolean guitar, boolean piano, boolean drums, boolean bass, boolean vocals, boolean instruments){
		
		super();
		
		this.title = title;
		this.notes = notes;
		this.genre = genre;
		this.dlurl = dlurl;
		this.previewurl = previewurl;
		this.vauthor = vauthor;
		this.created = new Date();
		this.songid = songid;
		this.childid = childid;
		this.version = version;
		this.parentid = parentid;
		this.childversion = childversion;
		this.songuid = songuid;
		this.guitar = guitar;
		this.bass = bass;
		this.vocals = vocals;
		this.drums = drums;
		this.piano = piano;
		this.instruments = instruments;
		
		vauthor.addVersion(this);
		songid.addVersions(this);
		
		
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
	@OrderColumn
	@Column
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	
	@ManyToOne
	public User getVauthor() {
		return vauthor;
	}

	public void setVauthor(User vauthor) {
		this.vauthor = vauthor;
	}
	

	@ManyToOne
	public Song getSongid() {
		return songid;
	}

	public void setSongid(Song songid) {
		this.songid = songid;
	}

	@NotNull
	@Column(name="childid")
	public String getChildid() {
		return childid;
	}

	public void setChildid(String childid) {
		this.childid = childid;
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
	@Column(name="parentid")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@NotNull
	@Column(name="childversion")
	public int getChildversion() {
		return childversion;
	}

	public void setChildversion(int childversion) {
		this.childversion = childversion;
	}
	
	@NotNull
	@Column(name="songuid")
	public int getSonguid(){
		return songuid;
	}
	
	public void setSonguid(int songuid){
		this.songuid = songuid;
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
	

	
	
}

