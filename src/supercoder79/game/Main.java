package supercoder79.game;

import supercoder79.game.engine.Window;
import supercoder79.game.engine.render.Model;
import supercoder79.game.engine.render.RenderConstants;
import supercoder79.game.engine.render.Renderer;
import supercoder79.game.engine.render.TexturedModel;
import supercoder79.game.engine.shader.BasicShader;
import supercoder79.game.engine.shader.TextureShader;

public class Main {
    public static Renderer renderer = new Renderer();
    public static BasicShader backgroundShader = new BasicShader("backgroundShader.txt");
    public static TextureShader foregroundShader = new TextureShader("vertexTextureShader.txt", "fragmentTextureShader.txt");

    public static void main(String[] args) {
        Window window = new Window(800, 600, 60, "Test");
        window.create();
        window.setBackgroundColor(0, 0, 1);

        foregroundShader.create();
        backgroundShader.create();

        TexturedModel model = new TexturedModel(new float[]{
//                -1f,0f,0.0f, //top left 0
//                1f,0f,0.0f,  //top right 1
//                1f,-1f,0.0f,//bottom left 2
//                -1f,-1f,0.0f  //bottom right 3
                -0.5f,0.5f, 0,
                0.5f,0.5f,0,
                0.5f,-0.5f,0,
                -0.5f,-0.5f,0

        }, new float[] {
                0,0,
                1,0,
                1,1,
                0,1
        }, RenderConstants.RECTANGLE, "test.png");

        Model background = new Model(new float[]{
                -1f,1f,0f,
                1f,1f,0f,
                1f,-1f,0f,
                -1f,-1f,0f
        }, RenderConstants.RECTANGLE);

        while (!window.closed()) {
            if (window.isUpdating()) {
                window.update();
                renderer.render(background, backgroundShader);
                renderer.renderTexture(model, foregroundShader);
                window.swapBuffers();
            }
        }
        model.destroy();
        background.destroy();
        foregroundShader.destroy();
        backgroundShader.destroy();
    }
}
