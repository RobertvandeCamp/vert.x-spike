package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

final class NoMatchHandler implements Handler<HttpServerRequest> {
	@Override
	public void handle(HttpServerRequest req) {
		String file = req.path();
		req.response().sendFile("web/" + file);
	}
}