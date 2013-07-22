package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServerRequest;

final class DogsHandler implements Handler<HttpServerRequest> {

	private final EventBus eb;

	public DogsHandler(EventBus eb) {
		this.eb = eb;
	}

	@Override
	public void handle(HttpServerRequest req) {
		System.out.println(String.format(WebServer.REQUEST_MSG, "dogs"));
		eb.publish("eb.dogs", "phonecall");
		req.response().end("Where's the dogs");
	}
}