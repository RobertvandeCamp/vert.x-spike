package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

final class CatsHandler implements Handler<HttpServerRequest> {
	
	private final EventBus eb;

	public CatsHandler(EventBus eb) {
		this.eb = eb;
	}
	
	@Override
	public void handle(HttpServerRequest req) {
		System.out.println(String.format(WebServer.REQUEST_MSG, "cats"));
		JsonObject jo = createCatJson(req.params().get("catname"));
		eb.send("eb.cats", jo);
		req.response().end("Where's the cats?");
	}

	protected JsonObject createCatJson(String catName) {
		JsonObject jo = new JsonObject();
		jo.putNumber("time", System.currentTimeMillis());
		jo.putString("message", catName);
		return jo;
	}
}