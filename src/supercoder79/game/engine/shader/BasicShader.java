package supercoder79.game.engine.shader;

public class BasicShader extends Shader {
    public static final String VERTEX_FILE = "./src/supercoder79/game/engine/shader/shadersrc/vertexShader.txt";
    public static final String FRAGMENT_FILE = "./src/supercoder79/game/engine/shadersrc/fragmentShader.txt";
    public BasicShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
        System.out.println("---");
    }
    public BasicShader(String fragmentShader) {
        super(VERTEX_FILE, "./src/supercoder79/game/engine/shader/shadersrc/" + fragmentShader);
    }

    @Override
    public void bindAllAttributes() {
        super.bindAttribute(0, "vertices");
    }
}
