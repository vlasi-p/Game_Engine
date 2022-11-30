package core;
import math.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Window class Window.java for initialising window
 */
public class Window {
    private int width, height;
    private final String title;
    private static long window;
    private final GLFWImage.Buffer icon;
    public int frames;
    public static long time;
    public Input input;
    private Vector3f background = new Vector3f(0, 0, 0);
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;
    private int[] windowPosX = new int[1], windowPosY = new int[1];

    /**
     * Constructor of window
     * @param width width of window
     * @param height height of window
     * @param title title of window
     */
    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.icon = null;
    }

    /**
     * Second constructor of window if we have icon
     * @param width width of window
     * @param height height of window
     * @param title title of window
     * @param icon icon of window
     */
//    public Window(int width, int height, String title, GLFWImage.Buffer icon) {
//        this.width = width;
//        this.height = height;
//        this.title = title;
//        this.icon = icon;
//    }

    /**
     * Initialise window
     */
    public void create() {
        if (!glfwInit()) {
            throw new RuntimeException("core.Window could not be initialised");
        }

        input = new Input();

        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            throw new RuntimeException("core.Window could not be created");
        }

        // Icon
        if (getIcon() != null) {
            glfwSetWindowIcon(window, getIcon());
        }

        // core.Window gets positioned in the center of the screen
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        windowPosX[0] = (vidMode.width() - width) / 2;
        windowPosY[0] = (vidMode.height() - height) / 2;
        glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallbacks();

        GLFW.glfwShowWindow(window);
        GLFW.glfwSwapInterval(1);

        time = System.currentTimeMillis();
    }

    private void createCallbacks(){
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getCursorPosCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    /**
     * Updates window
     */
    public void update() {
        if(isResized){
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }
        GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        frames++;
        if(System.currentTimeMillis() > time + 1000){
            GLFW.glfwSetWindowTitle(window, title + "| FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    /**
     * Renders window
     */
    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public boolean shouldClose(){
        return GLFW.glfwWindowShouldClose(window);
    }

    /**
     * Cleanups window
     */
    public void destroy() {
        input.destroy();
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void setFullscreen(boolean fullscreen) {
        isFullscreen = fullscreen;
        isResized = true;
        if(isFullscreen){
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        }else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }

    public void setBackgroundColor(float r, float g, float b){
        background.set(r, g, b);
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    /**
     * @return width of window
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height of window
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return title of window
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return icon of window
     */
    public GLFWImage.Buffer getIcon() {
        return icon;
    }

    /**
     * @return window object
     */
    public static long getWindow() {
        return window;
    }

}
