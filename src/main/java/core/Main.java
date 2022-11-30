package core;

import math.Vector3f;
import org.lwjgl.glfw.GLFW;
import render.Mesh;
import render.Renderer;
import render.Vertex;

public class Main implements Runnable{

    public Thread game;
    public static Window window;
    public static Renderer renderer;
    public static final int WIDTH = 1280, HEIGHT = 760;
    public static final String TITLE = "3D Engine";

    public static Mesh mesh = new Mesh(new Vertex[]{
            new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f(0.5f, 0.5f, 0.0f))
    }, new int[]{
            0, 1, 2,
            0, 3, 2
    });

    public void start(){
        game = new Thread(this, "game");
        game.start();
    }

    public static void init(){
        window = new Window(WIDTH, HEIGHT, TITLE);
        renderer = new Renderer();
        window.setBackgroundColor(1.0f, 0, 0);
        window.create();
        mesh.create();
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
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
