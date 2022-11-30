package core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

/**
 * Input class Input.java for operations on keyboard, mouse buttons, cursor positions and mouse scroll coordinates
 */
public class Input{

    private GLFWKeyCallback keyboard;
    private GLFWMouseButtonCallback mouseButtons;
    private GLFWCursorPosCallback cursorPos;
    private GLFWScrollCallback mouseScroll;

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double cursorX, cursorY;
    private static double scrollX, scrollY;

    private static int keyPressed;
    private static int buttonPressed;

    /**
     * Initialise input for keyboard, mouse buttons, cursor position and mouse scroll coordinates
     * Is called on every action
     */
    public Input(){
        keyboard = new GLFWKeyCallback(){
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action == GLFW.GLFW_PRESS);
                keyPressed = key;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action == GLFW.GLFW_PRESS);
                buttonPressed = button;
            }
        };

        cursorPos = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                cursorX = xpos;
                cursorY = ypos;
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };
    }

    /**
     * @param key value of key
     * @return which is down
     */
    public static boolean isKeyDown(int key){
        return keys[key];
    }

    /**
     * @param button value of mouse button
     * @return which mouse button is down
     */
    public static boolean isButtonDown(int button){
        return buttons[button];
    }

    /**
     * @param key value of key
     * @return which key is pressed
     */
    public static boolean isKeyPressed(int key){
        boolean check = (keyPressed == key);
        if(check) keyPressed = -1;
        return check;
    }

    /**
     * @param button value of mouse button
     * @return which mouse button is pressed
     */
    public static boolean isButtonPressed(int button){
        boolean check = (buttonPressed == button);
        if(check) buttonPressed = -1;
        return check;
    }

    /**
     * @param key value of key
     * @return if given key is up
     */
    public static boolean isKeyUp(int key){
        boolean check = (key != keyPressed);
        if(check) keyPressed = -1;
        return check;
    }

    /**
     * @param button value of mouse button
     * @return if given mouse button is up
     */
    public static boolean isButtonUp(int button){
        boolean check = (button != keyPressed);
        if(check) keyPressed = -1;
        return check;
    }

    public void destroy(){
        keyboard.free();
        mouseButtons.free();
        cursorPos.free();
        mouseScroll.free();
    }

    /**
     * Updates key, mouse button and cursor position on every call
     */
    public void update(){
        GLFW.glfwSetKeyCallback(Window.getWindow(), getKeyboardCallback());
        GLFW.glfwSetMouseButtonCallback(Window.getWindow(), getMouseButtonsCallback());
        GLFW.glfwSetCursorPosCallback(Window.getWindow(), getCursorPosCallback());
        GLFW.glfwSetScrollCallback(Window.getWindow(), getMouseScrollCallback());
    }

    /**
     * @return keyboard values
     */
    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    /**
     * @return mouse button values
     */
    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    /**
     * @return cursor position values
     */
    public GLFWCursorPosCallback getCursorPosCallback() {
        return cursorPos;
    }

    /**
     * @return mouse scroll coordinate values
     */
    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    /**
     * @return x coordinate of cursor position
     */
    public static double getCursorX() {
        return cursorX;
    }

    /**
     * @return y coordinate of cursor postion
     */
    public static double getCursorY() {
        return cursorY;
    }

    /**
     * @return x coordinate of mouse scroll
     */
    public static double getScrollX() {
        return scrollX;
    }

    /**
     * @return y coordinate of mouse scroll
     */
    public static double getScrollY() {
        return scrollY;
    }
}
