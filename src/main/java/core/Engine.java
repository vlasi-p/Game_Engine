package core;


import math.Transform;
import org.lwjgl.glfw.GLFW;
import utils.TimeUtility;

import java.io.IOException;

/**
 * Main class Engine.java for primary actions
 */
public class Engine implements Runnable {

    private Thread loopthread;
    private boolean running = false;
    private boolean isRendered = false;
    private TimeUtility time = new TimeUtility();
    private static Window frame = new Window((int) Transform.getWidth(), (int) Transform.getHeight(), "test frame");
    private Input input = new Input();
    private Game game;


    public Engine(Game game) {
        this.game = game;
    }

    /**
     * Starts threads for engine
     */
    public void start() {
        if (this.running != true) {
            this.running = true;
            this.loopthread = new Thread(this);
            this.loopthread.start();
        }
    }

    /**
     * Stops running threads
     * @return true
     * @throws InterruptedException if thread is interrupted
     */
    public boolean stop() throws InterruptedException {
        this.running = false;
        this.loopthread = null;
        return true;
    }

    /**
     * Initialise engine while running
     */
    @Override
    public void run() {
        frame.create();
        try {
            game.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.time.setPreviousTime((double) System.nanoTime());
        float DeltaTime = 0;
        while (this.running) {

            this.time.setCurrentTime((double) System.nanoTime());
            this.time.setDeltaTime(this.time.calculateDeltaTime());
            DeltaTime += this.time.calculateDeltaTime();
            Double TempGameRate = this.time.GameRate * 1000000000;
            while (DeltaTime >= TempGameRate) {
                DeltaTime -= TempGameRate;
                update();
            }
            if (!isRendered) {
                try {
                    render();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.time.setPreviousTime(this.time.getCurrentTime());
        }

    }

    private void update() {
        this.input.update();
        this.frame.update();
        this.game.update();
        this.isRendered = false;
    }

    private void render() throws InterruptedException {
        this.frame.swapBuffers();
        this.game.render();
    }

    private void clean() {
        this.frame.destroy();
    }

    public boolean isRunning() {
        return this.running;
    }

    public Input getInput() {
        return this.input;
    }

}
