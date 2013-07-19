package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class WebServer extends Verticle {

	private static final String REQUEST_MSG = "Incoming request for [%s]";
	private Logger logger;

	@Override
	public void start() {
		logger = container.logger();
		logger.info("Webserver online");
		final EventBus eb = vertx.eventBus();

		HttpServer server = vertx.createHttpServer();

		RouteMatcher routeMatcher = new RouteMatcher();

		routeMatcher.get("/", new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				req.response().sendFile("web/" + "index.html");
			}
		});

		routeMatcher.get("/animals", new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				logger.info(String.format(REQUEST_MSG, "animals"));
				req.response().sendFile("web/" + "animals.html");
			}
		});

		routeMatcher.get("/animals/dogs", new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				logger.info(String.format(REQUEST_MSG, "dogs"));
				eb.publish("eb.dogs", "phonecall");
				req.response().end("Where's the dogs");
			}
		});

		routeMatcher.get("/animals/cats", new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				logger.info(String.format(REQUEST_MSG, "cats"));
				eb.send("eb.cats", "new message");
				req.response().end("Where's the cats?");
			}
		});

		routeMatcher.noMatch(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				String file = req.path();
				req.response().sendFile("web/" + file);
			}
		});

		server.requestHandler(routeMatcher).listen(8080, "localhost");
	}

}
