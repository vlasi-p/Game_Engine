package core;

import org.lwjgl.glfw.GLFW;

public class Main implements Runnable{

    public Thread game;
    public static Window window;
    public static final int WIDTH = 1280, HEIGHT = 760;
    public static final String TITLE = "3D Engine";

    public void start(){
        game = new Thread(this, "game");
        game.start();
    }

    public static void init(){
        window = new Window(WIDTH, HEIGHT, TITLE);
        window.setBackgroundColor(1.0f, 0, 0);
        window.create();
    }

    public void run(){
        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11))
                window.setFullscreen(!window.isFullscreen());
        }
        window.destroy();
    }

    public void update(){
        window.update();
        if(Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
            System.out.println("X: " + Input.getCursorX() + ", Y: " + Input.getCursorY());
    }

    public void render(){
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
