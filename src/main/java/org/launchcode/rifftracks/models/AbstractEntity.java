package org.launchcode.rifftracks.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	private int uid;
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "uid", unique = true)
	public int getUid(){
		return this.uid;
	}
	
	protected void setUid(int uid){
		this.uid = uid;
	}
}
