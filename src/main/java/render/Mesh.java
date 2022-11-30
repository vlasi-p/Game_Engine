package render;

import math.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.GL_FLOAT;
import static org.lwjgl.opengl.GL20.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.GL_TRIANGLES;
import static org.lwjgl.opengl.GL20.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL20.glBindBuffer;
import static org.lwjgl.opengl.GL20.glBufferData;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glDrawElements;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Mesh class Mesh.java to 3D geometric surface
 */
public class Mesh {
    private Vertex[] vertices;
    private  int[] indices;
    private int vao, pbo, ibo;

    //    private final int vbo; // vertex buffer object
//    private final int ibo; // index buffer object
    private int size;

    /**
     * Constructor of mesh
     */
    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public void create() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++){
            positionData[i * 3] = vertices[i].get().getX();
            positionData[i * 3 + 1] = vertices[i].get().getY();
            positionData[i * 3 + 2] = vertices[i].get().getZ();
        }
        positionBuffer.put(positionData).flip();

        pbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, pbo);
        GL15.glBufferData(GL_ARRAY_BUFFER, positionBuffer, GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL_UNSIGNED_INT, false, 0, 0);
        GL15.glBindBuffer(GL_ARRAY_BUFFER, 0);
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        GL15.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Second constructor of mesh for vertices and indices
     */
//    public Mesh(Vertex[] vertices, int[] indices) {
//        this.vbo = glGenBuffers();
//        this.ibo = glGenBuffers();
//        this.size = 0;
//        addVertices(vertices, indices);
//    }

    /**
     * This function adds data from vertices to vertex buffer object and data from indices to index buffer object
     * @param vertices array of vertex
     * @param indices array of indices
     */
    public void addVertices(Vertex[] vertices, int[] indices) {
        this.size += indices.length;

        glBindBuffer(GL_ARRAY_BUFFER, this.vao);
        glBufferData(GL_ARRAY_BUFFER, createFlippedBuffer(vertices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createFlippedBuffer(indices), GL_STATIC_DRAW);
    }

    /**
     * This method uses gl to draw mesh
     */
    public void draw() {
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, vao);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, this.size, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
    }

    /**
     * This function returns buffer for vertices
     * @param vertices value of vertices
     * @return buffer value of vertices
     */
    public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);

        for (int i = 0; i < vertices.length; i++) {
            buffer.put(vertices[i].get().getX());
            buffer.put(vertices[i].get().getY());
            buffer.put(vertices[i].get().getZ());
        }

        buffer.flip();

        return buffer;
    }

    /**
     * This function return buffer for matrix
     * @param value value of 4x4 matrix
     * @return buffer value of matrix
     */
    public static FloatBuffer createFlippedBuffer(Matrix4f value) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                buffer.put(value.getCellValue(i, j));

        buffer.flip();

        return buffer;
    }

    /**
     * This function returns buffer for indices
     * @param indices value of indices
     * @return buffer value of indices
     */
    public static IntBuffer createFlippedBuffer(int[] indices) {
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        return buffer;
    }


    public Vertex[] getVertices(){
        return  vertices;
    }

    public int[] getIndices(){
        return indices;
    }

    /**
     * Getter for vertex buffer object
     * @return vertex buffer object
     */
    public int getVAO() {
        return vao;
    }

    /**
     * Getter for index buffer object
     * @return index buffer object
     */
    public int getIBO() {
        return ibo;
    }

    public int getPBO() {
        return pbo;
    }


    /**
     * Getter for mesh size
     * @return size of mesh
     */
    public int getSize() {
        return size;
    }
}