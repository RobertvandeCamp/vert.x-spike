package com.cad.scaling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

public class WebServer extends Verticle {

	static final String REQUEST_MSG = "Incoming request for [%s]";
	private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);

	@Override
	public void start() {

		RouteMatcher routeMatcher = new RouteMatcher();
		routeMatcher.get("/", new IndexHandler());
		routeMatcher.get("/animals", new AnimalsHandler());
		routeMatcher.get("/animals/dogs", new DogsHandler(vertx.eventBus()));
		routeMatcher.get("/animals/cats/:catname", new CatsHandler(vertx.eventBus()));
		routeMatcher.noMatch(new NoMatchHandler());

		HttpServer server = vertx.createHttpServer();
		server.requestHandler(routeMatcher).listen(8080, "localhost");
		LOG.info("Webserver online");
	}

}
