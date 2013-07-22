package com.cad.scaling.cat;

import java.util.UUID;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class CatVerticle extends Verticle {

	private Logger logger;
	private final UUID id;

	public CatVerticle() {
		id = UUID.randomUUID();
	}

	@Override
	public void start() {
		logger = container.logger();
		logger.info(String.format("Catverticle [%s] online", id));
		Handler<Message<JsonObject>> handler = new Handler<Message<JsonObject>>() {
			@Override
			public void handle(Message<JsonObject> msg) {
				logger.info(String.format("The cats in verticle [%s] have received [%s]", id.toString(), msg.body()
						.toString()));
			}
		};

		EventBus eb = vertx.eventBus();
		eb.registerHandler("eb.cats", handler);
	}

}
