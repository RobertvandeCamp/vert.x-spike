package com.cad.scaling;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

public class WebServer extends Verticle {

	@Override
	public void start() {
		HttpServer server = vertx.createHttpServer();

		RouteMatcher routeMatcher = new RouteMatcher();

		routeMatcher.get("/", new Handler<HttpServerRequest>() {
		    public void handle(HttpServerRequest req) {
		    	req.response().sendFile("web/" + "index.html");  
		    }
		});
		
		routeMatcher.get("/animals", new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest req) {
				req.response().sendFile("web/" + "animals.html");  
			}
		});

		routeMatcher.get("/animals/dogs", new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest req) {
				req.response().end("Where's the dogs");
			}
		});
		
		routeMatcher.get("/animals/cats", new Handler<HttpServerRequest>() {
		    public void handle(HttpServerRequest req) {
		        req.response().end("Where's the cats?");
		    }
		});

		routeMatcher.noMatch(new Handler<HttpServerRequest>() {
		    public void handle(HttpServerRequest req) {
		    	String file = req.path();
			    req.response().sendFile("web/" + file);
		    }
		});
		
		server.requestHandler(routeMatcher).listen(8080, "localhost");
	}
	
}
