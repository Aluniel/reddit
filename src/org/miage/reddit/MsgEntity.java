package org.miage.reddit;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class MsgEntity {
	
	//
	// FIELDS
	//
	
	@PrimaryKey
	@Persistent
	private String idMsg;

	@Persistent
	private String content;
	
	@Persistent
	private String sender;
	
	@Persistent
	private int nbVotes;
	
	//
	// PROPERTIES
	//
	
	public String getIdMsg() {
		return idMsg;
	}

	public void setIdMsg(String idMsg) {
		this.idMsg = idMsg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getNbVotes() {
		return nbVotes;
	}

	public void setNbVotes(int nbVotes) {
		this.nbVotes = nbVotes;
	}
}
