package utils;

/**
 * Utils class TimeUtility.java is mainly for calculating delta time. Time difference between previous time and current time.
 * This class is created to calculate time it took engines main loop to iterate once.
 */
public class TimeUtility {
    final double fps = 60;
    Double previousTime = (double) System.nanoTime();
    Double currentTime =  (double) System.nanoTime();
    Double deltaTime = 0.0;
    public final Double GameRate = (Double) 1.0 / this.fps;

    /**
     * getter for cuttentTime
     * @return currentTime
     */
    public Double getCurrentTime() {
        return this.currentTime;
    }

    /**
     * getter for previousTime
     * @return previousTime
     */
    public Double getPreviousTime() {
        return this.previousTime;
    }

    /**
     * sets currentTime for this object
     * @param currentTime value to be set to currentTime
     */
    public void setCurrentTime(Double currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * sets previousTime for this object
     * @param previousTime value to be set to previousTime
     */
    public void setPreviousTime(Double previousTime) {
        this.previousTime = previousTime;
    }

    /**
     * sets deltaTime for this object
     * @param deltaTime value to be set to deltaTime
     */
    public void setDeltaTime(Double deltaTime) {
        this.deltaTime = deltaTime;
    }

    /**
     * getter for deltaTime
     * @return deltaTime
     */
    public Double getDeltaTime() {
        return this.deltaTime;
    }

    /**
     * Subtructs PreviousTime to CurrentTime getting deltaTime
     * @return result of subtraction
     */
    public Double calculateDeltaTime() {
        return this.getCurrentTime() - this.getPreviousTime();
    }

}