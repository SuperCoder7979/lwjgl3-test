package supercoder79.game.engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import supercoder79.game.engine.shader.BasicShader;

public class Renderer {
    public void renderModel(Model model) {
        GL30.glBindVertexArray(model.vertexArrayID);
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void render(Model model, BasicShader shader) {
        shader.bind();
        renderModel(model);
        shader.unbind();
    }

    public void renderTexture(Model model) {
        if (model.textured) {
            GL30.glBindVertexArray(model.vertexArrayID);
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.material.textureID);
            GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
        }
    }
}
