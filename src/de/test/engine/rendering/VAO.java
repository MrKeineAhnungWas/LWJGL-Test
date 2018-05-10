package de.test.engine.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VAO {
	private int id;
	
	private int[] vboIDs;
	private int vertexCount;
	
	public VAO(float[] positions) {
		id = GL30.glGenVertexArrays();
		
		GL30.glBindVertexArray(id);
		
		int positionVBOID = addStaticAttribute(0, positions, 3);
		
		vboIDs = new int[] {positionVBOID};
		vertexCount = positions.length / 3;
	}
	
	public void render() {
		GL30.glBindVertexArray(id);
		
		for(int i = 0; i < vboIDs.length; i ++) {
			GL20.glEnableVertexAttribArray(i);
		}
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
		
		for(int i = 0; i < vboIDs.length; i ++) {
			GL20.glDisableVertexAttribArray(i);
		}
	}
	
	public void release() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		GL30.glDeleteVertexArrays(id);
		
		for(int id : vboIDs) {
			GL15.glDeleteBuffers(id);
		}
	}
	
	private int addStaticAttribute(int index, float[] data, int dataSize) {
		int vboID = GL15.glGenBuffers();
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, dataSize, GL11.GL_FLOAT, false, 0, 0);
		
		return vboID;
	}
}