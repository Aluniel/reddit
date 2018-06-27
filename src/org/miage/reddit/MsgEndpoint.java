package org.miage.reddit;

import com.google.api.server.spi.config.Api;

@Api(name = "msgendpoint")
public class MsgEndpoint {
	
	public MsgEntity getMsg() {
		MsgEntity msg = new MsgEntity();
		
		return msg;
	}
}
