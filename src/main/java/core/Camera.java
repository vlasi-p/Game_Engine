package core;

import math.Vector3f;

/**
 * Camera class Camera.java to create instance of camera and by inputs move or rotate it
 */
public class Camera {
    /**
     * Parameter to initialise Y axis
     */
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);

    private Vector3f position;
    private Vector3f forward;
    private Vector3f up;

    /**
     * Initialises camera
     */
    public Camera() {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }

    /**
     * Constructor of camera
     * @param position position of camera
     * @param forward forward direction of camera
     * @param up upward direction of camera
     */
    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward;
        this.up = up;

        forward.normalize();
        up.normalize();
    }

    /**
     * This function moves camera to given direction
     * @param direction direction of movement
     * @param amount amount of movement
     */
    public void move(Vector3f direction, float amount) {
        Vector3f vector = new Vector3f(direction.getX() * amount, direction.getY() * amount, direction.getZ() * amount);
        position.add(vector);
    }

    /**
     * Getter for left direction of camera related to current position
     * @return left direction of camera
     */
    public Vector3f getLeft() {
        return up.cross(forward).normalize();
    }

    /**
     * Getter for right direction of camera related to current position
     * @return right direction of camera
     */
    public Vector3f getRight() {
        return forward.cross(up).normalize();
    }

    /**
     * This function rotates camera on X axis
     * @param angle angle of rotation
     */
    public void rotateX(float angle) {
        Vector3f horizontalAxis = yAxis.cross(forward).normalize();
        forward.rotate(angle, horizontalAxis).normalize();
        up = forward.cross(horizontalAxis).normalize();
    }

    /**
     * This function rotates camera on Y axis
     * @param angle angle of rotation
     */
    public void rotateY(float angle) {
        Vector3f horizontalAxis = yAxis.cross(forward).normalize();
        forward.rotate(angle, yAxis).normalize();
        up = forward.cross(horizontalAxis).normalize();
    }

    /**
     * This function returns current position of camera
     * @return position of camera
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * This function sets position to camera
     * @param position position for camera
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * This function returns forward direction of camera related to current position of camera
     * @return forward direction of camera
     */
    public Vector3f getForward() {
        return forward;
    }

    /**
     * This function sets forward direction to camera
     * @param forward forward direction for camera
     */
    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    /**
     * This function returns upward direction of camera related to current position
     * @return upward direction of camera
     */
    public Vector3f getUp() {
        return up;
    }

    /**
     *  This function sets upward direction to camera
     * @param up upward direction for camera
     */
    public void setUp(Vector3f up) {
        this.up = up;
    }
}
