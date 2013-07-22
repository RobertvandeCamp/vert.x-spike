package com.cad.scaling;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.vertx.java.platform.Container;

import com.cad.scaling.cat.CatVerticle;
import com.cad.scaling.dog.DogVerticle;

public class MainServerTest {

	private final Container containerMock = mock(Container.class);

	@Before
	public void before() {
	}

	@Test
	public void start() {
		MainServer server = new MainServerStub();

		server.start();

		verify(containerMock).deployVerticle(CatVerticle.class.getName(), 2);
		verify(containerMock).deployVerticle(DogVerticle.class.getName(), 2);
	}

	private class MainServerStub extends MainServer {
		@Override
		public Container getContainer() {
			return containerMock;
		}
	}

}
