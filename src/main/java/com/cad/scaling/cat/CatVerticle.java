package com.cad.scaling.cat;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

public class CatVerticle extends Verticle {

	private static final Logger LOG = LoggerFactory.getLogger(CatVerticle.class);
	private final UUID id;

	public CatVerticle() {
		id = UUID.randomUUID();
	}

	@Override
	public void start() {
		LOG.info(String.format("Catverticle [%s] online", id));
		final EventBus eb = vertx.eventBus();
		Handler<Message<JsonObject>> handler = new Handler<Message<JsonObject>>() {
			@Override
			public void handle(Message<JsonObject> msg) {
				LOG.info(String.format("The cats in verticle [%s] have received [%s]", id.toString(), msg.body()
						.toString()));
				msg.reply(true);
			}
		};

		eb.registerHandler("eb.cats", handler);
	}

}
