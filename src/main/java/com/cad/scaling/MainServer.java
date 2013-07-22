package com.cad.scaling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.platform.Verticle;

import com.cad.scaling.cat.CatVerticle;
import com.cad.scaling.dog.DogVerticle;

public class MainServer extends Verticle {

	private static final Logger LOG = LoggerFactory.getLogger(MainServer.class);

	@Override
	public void start() {
		LOG.info("Main server online");

		getContainer().deployVerticle(CatVerticle.class.getName(), 2);
		getContainer().deployVerticle(DogVerticle.class.getName(), 2);
	}

}