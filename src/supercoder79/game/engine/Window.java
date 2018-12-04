package supercoder79.game.engine;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.nio.DoubleBuffer;

public class Window {
    public int width;
    public int height;
    public double fps_cap, time, processedTime;
    public long window;
    public boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    public boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    public String title;
    public Vector3f backgroundColor;

    public Window(int width, int height, int fps, String name) {
        this.width = width;
        this.height = height;
        this.title = name;
        this.fps_cap = 1.0/fps;
        backgroundColor = new Vector3f(0.0f);
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.out.println("Couldn't init GLFW!");
            System.exit(1);
        }

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            System.out.println("Couldn't create window!");
            System.exit(1);
        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
        GLFW.glfwShowWindow(window);
        time = getTime();
    }

    public void setBackgroundColor(float r, float g, float b) {
        backgroundColor = new Vector3f(r, g, b);
    }

    public double getTime() {
        return (double)System.nanoTime() / (double)1000000000;
    }

    public boolean closed() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void stop() {
        GLFW.glfwTerminate();
    }

    public void update() {
        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) keys[i] = isKeyDown(i);
        for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) mouseButtons[i] = isMouseDown(i);
        GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 0.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean isKeyDown(int code) {
        return GLFW.glfwGetKey(window, code) == 1;
    }

    public boolean isMouseDown(int code) {
        return GLFW.glfwGetMouseButton(window, code) == 1;
    }

    public boolean isKeyPressed(int code) {
        return isKeyDown(code) && !keys[code];
    }

    public boolean isKeyReleased(int code) {
        return !isKeyDown(code) && keys[code];
    }

    public boolean isMousePressed(int code) {
        return isMouseDown(code) && !mouseButtons[code];
    }

    public boolean isMouseReleased(int code) {
        return !isMouseDown(code) && mouseButtons[code];
    }

    public double mouseX() {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, buffer, null);
        return buffer.get(0);
    }
    public double mouseY() {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, null, buffer);
        return buffer.get(0);
    }

    public boolean isUpdating() {
        double nextTime = getTime();
        double passedTime = nextTime - time;
        processedTime += passedTime;
        time = nextTime;
        while (processedTime > fps_cap){
            processedTime -= fps_cap;
            return true;
        }
        return false;
    }
}
