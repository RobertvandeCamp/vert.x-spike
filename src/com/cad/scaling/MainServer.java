package com.cad.scaling;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class MainServer extends Verticle {

	private Logger logger;

	@Override
	public void start() {
		logger = container.logger();
		logger.info("Main server online");

		container.deployVerticle("com.cad.scaling.WebServer");
		container.deployVerticle("com.cad.scaling.cat.CatVerticle", 2);
		container.deployVerticle("com.cad.scaling.dog.DogVerticle", 2);
	}
}