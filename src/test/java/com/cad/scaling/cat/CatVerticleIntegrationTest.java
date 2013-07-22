package com.cad.scaling.cat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Future;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.testtools.TestVerticle;
import org.vertx.testtools.VertxAssert;

public class CatVerticleIntegrationTest extends TestVerticle {

	@Override
	public void start(final Future<Void> startResult) {
		container.deployVerticle(CatVerticle.class.getName(), new Handler<AsyncResult<String>>() {

			@Override
			public void handle(AsyncResult<String> arg0) {
				CatVerticleIntegrationTest.super.start();
				startResult.setResult(null);
			}
		});
	}

	@Test
	public void handleCatMessage() {
		EventBus eb = vertx.eventBus();
		JsonObject jo = new JsonObject();
		jo.putString("testKey", "testValue");
		eb.send("eb.cats", jo, new Handler<Message<Boolean>>() {

			@Override
			public void handle(Message<Boolean> msg) {
				assertThat(msg, is(notNullValue()));
				assertThat(msg.body(), is(true));
				VertxAssert.testComplete();
			}
		});
	}

}
