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
	private long idMsgIndex;
	
	@Parent
	@Index
	private Key<Message> idMsg;	

	@Index
	private String name;
	
	//
	// METHODS
	//
	
	public Voter() {
		
	}
	
	public Voter(Message message, String name) {
		
	}
}
