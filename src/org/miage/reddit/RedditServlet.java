package org.miage.reddit;

import java.io.IOException;

import javax.servlet.http.*;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class RedditServlet extends HttpServlet {
	
	static {
		ObjectifyService.register(Message.class);
		ObjectifyService.register(Voter.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Message mess = new Message("Alex", "coucou");
		
		ofy().save().entity(mess);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
