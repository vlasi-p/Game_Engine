package render;

import math.Vector3f;

/**
 * Vertex class Vertex.java represents vertex including vectors
 */
public class Vertex {

    /**
     * Vertices immutable size
     */
    public static final int SIZE = 3;
    private Vector3f pos;

    /**
     * Constructor of vertex with default coordinates
     */
    public Vertex(Vector3f pos){
        this.pos = pos;
    }

    /**
     * Constructor of vertex using coordinates
     * @param x1 x coordinate of vertex
     * @param y1 y coordinate of vertex
     * @param z1 z coordinate of vertex
     */
    public Vertex(float x1, float y1, float z1){
        pos = new Vector3f(x1, y1, z1);
    }

    /**
     * Getter for vector of vertex
     * @return this vector
     */
    public Vector3f get(){
        return this.pos;
    }

    /**
     * Setter to change vector of vertex
     * @param x vector that changes previous
     */
    public void set(Vector3f x){
        this.pos = x;
    }

}
