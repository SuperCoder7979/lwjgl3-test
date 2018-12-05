package supercoder79.game.engine.render.texture;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class Material {
    public int textureID;

    public Material(String file) {
        try {
            textureID = TextureLoader.getTexture("png", new FileInputStream("src/assets/"+file)).getTextureID();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        GL11.glDeleteTextures(textureID);
    }
}
