package com.cad.scaling.dog;

import java.util.UUID;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class DogVerticle extends Verticle {

	private Logger logger;
	private final UUID id;

	public DogVerticle() {
		id = UUID.randomUUID();
	}

	@Override
	public void start() {
		logger = container.logger();
		logger.info(String.format("Dogverticle [%s] online", id));
		Handler<Message<String>> handler = new Handler<Message<String>>() {
			@Override
			public void handle(Message<String> msg) {
				logger.info(String.format("The dogs in verticle [%s] have received [%s]", id.toString(), msg.body()));
			}
		};

		EventBus eb = vertx.eventBus();
		eb.registerHandler("eb.dogs", handler);
	}

}
