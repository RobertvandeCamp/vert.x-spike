package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

final class AnimalsHandler implements Handler<HttpServerRequest> {
	@Override
	public void handle(HttpServerRequest req) {
		System.out.println(String.format(WebServer.REQUEST_MSG, "animals"));
		req.response().sendFile("web/" + "animals.html");
	}
}