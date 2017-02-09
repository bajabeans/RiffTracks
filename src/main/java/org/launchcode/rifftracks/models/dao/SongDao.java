package org.launchcode.rifftracks.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.rifftracks.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface SongDao extends CrudRepository<Song, Integer>{

	List<Song> findByAuthor(int authorID);
	
	List<Song> findAll();
	
	Song findByUid(int uid);
	
	List<Song> findByGenre(String genre);
	
	List<Song> findByVersion(int version);
	
	Song findByTitle(String title);
	
	List<Song> findByChildid(String childid);
	
	Song findBySongnum(int songnum);
	
	List<Song> findByGuitar(boolean guitar);
	
	List<Song> findByDrums(boolean drums);
	
	List<Song> findbyPiano(boolean piano);
	
	List<Song> findbyBass(boolean bass);
}

