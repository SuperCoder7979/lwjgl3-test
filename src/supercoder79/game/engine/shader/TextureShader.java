package supercoder79.game.engine.shader;

public class TextureShader extends BasicShader {

    public TextureShader(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
    }

    @Override
    public void bindAllAttributes() {
        super.bindAttribute(0, "vertices");
        super.bindAttribute(1, "texCoords");
    }
}
