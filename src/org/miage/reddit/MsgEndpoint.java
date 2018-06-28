package org.miage.reddit;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.tools.admin.OAuth2ServerConnection.OAuthException;
import com.google.storage.onestore.v3.OnestoreEntity.User;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(name = "msgapi",
	 version = "v1",
	 description = "Permet de gérer des messages",
	 namespace = @ApiNamespace(ownerDomain = "msg.miage.org", ownerName = "msg.miage.org", packagePath = "services"))
public class MsgEndpoint {
	
	static {
		ObjectifyService.register(Message.class);
		ObjectifyService.register(Voter.class);
	}
	
	@ApiMethod(name = "messages.get")
	public List<Message> getMsgs() {
		return ofy().load().type(Message.class).order("nbVotes").limit(10).list();
	}
	
	@ApiMethod(name = "message.getbyid")
	public Message getMsg(@Named("idmsg") long idMsg) {
		return ofy().load().type(Message.class).id(idMsg).now();
	}
	
	@ApiMethod(name = "message.insert")
	public Message postMsg(@Named("message") String message, User user) throws OAuthException, NotFoundException {
		Message objMessage = new Message("Alex", message);
		ofy().save().entity(objMessage).now();
		return ofy().load().entity(objMessage).now();
		//ofy().save().entity(message).now();
		//return ofy().load().entity(message).now();
	}
	
	@ApiMethod(name = "message.deleteall")
	public List<Message> deleteAll() {
		ofy().delete().entities(ofy().load().type(Message.class).iterable());
		return new ArrayList<Message>();
	}
}
