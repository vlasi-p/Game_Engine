package core;

import java.io.IOException;

/**
 * Interface of init, update and render functions for engine
 */
public interface Game {

    /**
     * Initialises object
     */
    void init() throws IOException;

    /**
     * Updates window
     */
    void update();

    /**
     * Renders window
     */
    void render();

}