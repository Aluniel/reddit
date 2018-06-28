package org.miage.reddit;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Message {
	
	//
	// FIELDS
	//
	
	@Id
	private Long idMsg;

	private String message;
	
	@Index
	private String author;
	
	@Index
	private int nbVotes;
	
	//
	// PROPERTIES
	//
	
	public Long getIdMsg() {
		return idMsg;
	}

	public void setIdMsg(Long idMsg) {
		this.idMsg = idMsg;
	}

	public String getContent() {
		return message;
	}

	public void setContent(String content) {
		this.message = content;
	}

	public String getSender() {
		return author;
	}

	public void setSender(String sender) {
		this.author = sender;
	}

	public int getNbVotes() {
		return nbVotes;
	}

	public void setNbVotes(int nbVotes) {
		this.nbVotes = nbVotes;
	}
	
	//
	// METHODS
	//
	
	public Message() {
		
	}
	
	public Message(String author, String message) {
		this.author = author;
		this.message = message;
	}
}
