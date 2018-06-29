package org.miage.reddit;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Voter {
	//
	// FIELDS
	//
	
	@Id
	private String name;
	
	@Parent
	@Index
	private Key<Message> idMsg;
	
	//
	// METHODS
	//
	
	public Voter() {
		
	}
	
	public Voter(Key<Message> message, String name) {
		this.idMsg = message;
		this.name  = name;
	}

	public String getName() {
		return name;
	}

	public Key<Message> getIdMsg() {
		return idMsg;
	}
}
