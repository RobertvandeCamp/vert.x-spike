package com.cad.scaling.dog;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class DogVerticle extends Verticle {

	private static final Logger LOG = LoggerFactory.getLogger(DogVerticle.class);
	private final UUID id;

	public DogVerticle() {
		id = UUID.randomUUID();
	}

	@Override
	public void start() {
		LOG.info(String.format("Dogverticle [%s] online", id));
		Handler<Message<String>> handler = new Handler<Message<String>>() {
			@Override
			public void handle(Message<String> msg) {
				LOG.info(String.format("The dogs in verticle [%s] have received [%s]", id.toString(), msg.body()));
			}
		};

		EventBus eb = vertx.eventBus();
		eb.registerHandler("eb.dogs", handler);
	}

}
