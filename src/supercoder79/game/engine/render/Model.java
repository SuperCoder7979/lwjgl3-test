package supercoder79.game.engine.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Model {
    public int vertexCount, vertexBufferID, indicesBufferID, vertexArrayID;
    public float[] vertices;
    public int[] indices;
    public Model(float[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
        this.vertexCount = indices.length;
    }

    public void create() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertexCount*3);
        buffer.put(vertices);
        buffer.flip();
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
        vertexArrayID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vertexArrayID);
        vertexBufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        indicesBufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL20.glEnableVertexAttribArray(0);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL30.glBindVertexArray(0);
        GL20.glDisableVertexAttribArray(0);
    }

    public void destroy() {
        GL30.glDeleteVertexArrays(vertexArrayID);
        GL20.glDeleteBuffers(vertexBufferID);
        GL20.glDeleteBuffers(indicesBufferID);
    }
}
