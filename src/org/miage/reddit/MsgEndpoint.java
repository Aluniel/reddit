package org.miage.reddit;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(name = "msgapi", version = "v1")
public class MsgEndpoint {
	
	static {
		ObjectifyService.register(Message.class);
		ObjectifyService.register(Voter.class);
	}
	
	@ApiMethod(name = "getMsg", path = "/getMsg/{idMsg}", httpMethod = HttpMethod.GET)
	public Message getMsg(@Named("idMsg") long idMsg) {
		return ofy().load().type(Message.class).id(idMsg).now();
	}
	
	@ApiMethod(name = "setMsg")
	public void setMsg() {
		
	}
}
