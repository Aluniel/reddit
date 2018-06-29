package org.miage.reddit;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class CanVote {
	
	@Id
	public long id;
	
	public boolean canvote;
	
	public CanVote(boolean canvote) {
		this.canvote = canvote;
	}
}
