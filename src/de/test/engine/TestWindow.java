package de.test.engine;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class TestWindow {
	public static final Dimension vidMode = Toolkit.getDefaultToolkit().getScreenSize();
	private long id;
	
	public void start() {
		GLFWErrorCallback.createPrint(System.err).set();
		GLFW.glfwInit();
		
		id = GLFW.glfwCreateWindow(800, 600, "Test", 0, 0);
		
		GLFW.glfwSetWindowPos(id, vidMode.width / 2 - 400, vidMode.height / 2 - 300);
		
		GLFW.glfwMakeContextCurrent(id);
		GLFW.glfwSwapInterval(1);
		
		GL.createCapabilities();
		GL11.glClearColor(0, 0, 0, 1);
		
		init();
		
		while (!GLFW.glfwWindowShouldClose(id)) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			render();
			
			GLFW.glfwSwapBuffers(id);
			
			GLFW.glfwPollEvents();
		}
		
		release();
		
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
	
	public void init() {}
	public void render() {}
	public void release() {}
}