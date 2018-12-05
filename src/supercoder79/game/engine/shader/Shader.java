package supercoder79.game.engine.shader;

import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Shader {
    public int vertexShaderID, fragmentShaderID, programID;
    public String vertexFile, fragmentFile;
    public Shader(String vertexFile, String fragmentFile) {
        this.fragmentFile = fragmentFile;
        this.vertexFile = vertexFile;
    }

    public void create() {
        programID = GL20.glCreateProgram();

        vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        String content = readFile(vertexFile);
        GL20.glShaderSource(vertexShaderID, content);
        GL20.glCompileShader(vertexShaderID);

        if (GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            System.out.println("Vertex foregroundShader: " + GL20.glGetShaderInfoLog(vertexShaderID));
        }

        content = readFile(fragmentFile);
        fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShaderID, content);
        GL20.glCompileShader(fragmentShaderID);

        if (GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
            System.out.println("Fragment foregroundShader: " + GL20.glGetShaderInfoLog(fragmentShaderID));
        }

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);

        bindAllAttributes();
        GL20.glLinkProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) {
            System.out.println("Program link: " + GL20.glGetProgramInfoLog(programID));
        }

        GL20.glValidateProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL20.GL_FALSE) {
            System.out.println("Program validate: " + GL20.glGetProgramInfoLog(programID));
        }
    }

    public abstract void bindAllAttributes();

    public void bindAttribute(int index, String location) {
        GL20.glBindAttribLocation(programID, index, location);
    }

    public void bind() {
        GL20.glUseProgram(programID);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void destroy() {
        unbind();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    private String readFile(String file) {
        BufferedReader reader = null;
        StringBuilder string = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                string.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return string.toString();
    }
}
