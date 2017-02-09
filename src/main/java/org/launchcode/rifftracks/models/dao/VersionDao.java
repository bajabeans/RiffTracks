package org.launchcode.rifftracks.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.rifftracks.models.Version;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface VersionDao extends CrudRepository<Version, Integer> {

	List<Version> findAll();
	
	List<Version> findBySongid(int songid);
	
	List<Version> findBySonguid(int songuid);
	
	List<Version> findByParentid(String parentid);
 }
