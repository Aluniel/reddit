package org.miage.reddit;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(name = "tinyredditmsg",
	 version = "v1",
	 description = "Permet de gérer des messages",
	 namespace = @ApiNamespace(ownerDomain = "msg.miage.org", ownerName = "msg.miage.org", packagePath = "services"))
public class MsgEndpoint {
	
	static {
		ObjectifyService.register(Message.class);
		ObjectifyService.register(Voter.class);
		ObjectifyService.register(CanVote.class);
	}
	
	@ApiMethod(name = "messages.get", path = "/get")
	public List<Message> getMsgs() {
		return ofy().load().type(Message.class).order("-nbVotes").limit(10).list();
	}
	
	@ApiMethod(name = "messages.getmine", path = "/getmine/{user}")
	public List<Message> getMyMsgs(@Named("user") String user) {
		return ofy().load().type(Message.class).filter("author", user).order("nbVotes").list();
	}
	
	/*@ApiMethod(name = "messages.getvotedbyme", path = "/getvotedbyme/{user}")
	public List<Message> getVotedByMe(@Named("user") String user) {
		List<Voter> voters = ofy().load().type(Voter.class).filter("name", user).list();

		for(Voter voter : voters)
		return ;
	}*/
	
	@ApiMethod(name = "message.getbyid", path = "/getbyid/{idmsg}")
	public Message getMsg(@Named("idmsg") long idMsg) {
		return ofy().load().type(Message.class).id(idMsg).now();
	}
	
	@ApiMethod(name = "message.insert", path = "/insert/user/message")
	public Message postMsg(@Named("user") String user, @Named("message") String message) throws Exception {
		if(user == null)
			throw new Exception("Vous devez être connecté !");
		Message objMessage = new Message(user, message);
		ofy().save().entity(objMessage).now();
		return ofy().load().entity(objMessage).now();
	}
	
	@ApiMethod(name = "message.deleteall", path = "/deleteall")
	public List<Message> deleteAll() {
		ofy().delete().entities(ofy().load().type(Message.class).iterable());
		return new ArrayList<Message>();
	}
	
	@ApiMethod(name = "message.votefor", path = "/votefor/{user}/{idmsg}")
	public Message voteFor(@Named("user") String user, @Named("idmsg") long idMsg) throws Exception {
		if(user == null)
			throw new Exception("Vous devez être connecté !");
		Message message = ofy().load().type(Message.class).id(idMsg).now();
		Voter voter = ofy().load().type(Voter.class).parent(message).id(user).now();
		if(voter == null) {
			message.setNbVotes(message.getNbVotes() + 1);
			ofy().save().entity(message).now();
			voter = new Voter(Key.create(Message.class, message.getIdMsg()), user);
			ofy().save().entity(voter).now();
		} else {
			throw new Exception(user + " a déjà voté !");
		}
		return ofy().load().type(Message.class).id(idMsg).now();
	}
	
	@ApiMethod(name = "message.voteagainst", path = "/voteagainst/{user}/{idmsg}")
	public Message voteAgainst(@Named("user") String user, @Named("idmsg") long idMsg) throws Exception {
		if(user == null)
			throw new Exception("Vous devez être connecté !");
		Message message = ofy().load().type(Message.class).id(idMsg).now();
		Voter voter = ofy().load().type(Voter.class).parent(message).id(user).now();
		if(voter == null) {
			message.setNbVotes(message.getNbVotes() - 1);
			ofy().save().entity(message).now();
			voter = new Voter(Key.create(Message.class, message.getIdMsg()), user);
			ofy().save().entity(voter).now();
		} else {
			throw new Exception(user + " a déjà voté !");
		}
		return ofy().load().type(Message.class).id(idMsg).now();
	}
	
	@ApiMethod(name = "message.canvote", path = "/canvote/{user}/{idmsg}")
	public CanVote canVote(@Named("user") String user, @Named("idmsg") long idMsg) {
		if(user == null)
			return new CanVote(false);
		Message message = ofy().load().type(Message.class).id(idMsg).now();
		Voter voter = ofy().load().type(Voter.class).parent(message).id(user).now();
		return new CanVote(voter != null);
	}
}
