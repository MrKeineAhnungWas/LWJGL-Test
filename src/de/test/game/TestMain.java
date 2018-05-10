package de.test.game;

import de.test.engine.TestWindow;
import de.test.engine.rendering.VAO;

public class TestMain extends TestWindow {
	private static TestMain instance;

	private static VAO triangle;
	
	public static void main(String[] args) {
		instance = new TestMain();
		
		instance.start();
	}
	
	@Override
	public void init() {
		triangle = new VAO(new float[] {
				   -0.5F, -0,5F, 1.0F,
					0.5f, -0.5f, 1.0F,
					0.0F,  0.5F, 1.0F
			});
	}

	@Override
	public void render() {
		triangle.render();
	}

	@Override
	public void release() {
		triangle.release();
	}
}