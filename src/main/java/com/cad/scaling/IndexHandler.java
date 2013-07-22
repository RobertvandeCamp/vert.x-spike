package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

final class IndexHandler implements Handler<HttpServerRequest> {
	@Override
	public void handle(HttpServerRequest req) {
		req.response().sendFile("web/" + "index.html");
	}
}