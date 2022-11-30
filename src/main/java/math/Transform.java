package math;

import core.Camera;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Math class Transform.java. Created to generate transformation matrices such as rotation, scale and translation for Vertexes.
 */
public class Transform {

    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;
    private static float zNear = 0.5f;
    private static float zFar = -0.5f;
    private static float width = 800;
    private static float height = 800;
    private static float fieldOfView = 90;
    private static Camera camera;

    /**
     * initializes Translation with translation, rotation, scale set to respective arguments
     * @param translation Vector3f to be set to translation
     * @param rotation Vector3f to be set to rotation
     * @param scale Vector3f to be set to scale
     */
    public Transform(Vector3f translation, Vector3f rotation, Vector3f scale) {
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     * initializes Translation with translation, rotation, scale set to pre-defined values
     * rotation and translation to zero vectors. scale to (1, 1, 1)
     */
    public Transform() {
        this.scale = new Vector3f(1, 1, 1);
        this.rotation = new Vector3f(0, 0, 0);
        this.translation = new Vector3f(0, 0, 0);
    }

    /**
     * sets static float variable fieldOfView to argument
     * @param other value to be set to fieldOfView
     */
    public static void setFieldOfView(float other) {
        fieldOfView = other;
    }

    /**
     * getter for fieldOfView
     * @return fieldOfView
     */
    public static float getFieldOfView() {
        return fieldOfView;
    }

    /**
     * sets static float variable zFar to argument
     * @param other value to be set to zFar
     */
    public static void setzFar(float other) {
        zFar = other;
    }

    /**
     * getter for zFat
     * @return zFat
     */
    public static float getzFar() {
        return zFar;
    }

    /**
     * sets static float variable zNear to argument
     * @param other value to be set to zNear
     */
    public static void setzNear(float other) {
        zNear = other;
    }

    /**
     * getter for zNear
     * @return zNear
     */
    public static float getzNear() {
        return zNear;
    }

    /**
     * sets static float variable height to argument
     * @param other value to be set to height
     */
    public static void setHeight(float other) {
        height = other;
    }

    /**
     * getter for height
     * @return height
     */
    public static float getHeight() {
        return height;
    }

    /**
     * sets static float variable width to argument
     * @param other value to be set to width
     */
    public static void setWidth(float other) {
        width = other;
    }

    /**
     * getter for width
     * @return width
     */
    public static float getWidth() {
        return width;
    }

    /**
     * getter for translation
     * @return translation
     */
    public Vector3f getTranslation() {
        return translation;
    }

    /**
     * sets translation to argument
     * @param other value to be set to translation
     * @return this object
     */
    public Transform setTranslation(Vector3f other) {
        this.translation = other;
        return this;
    }

    /**
     * getter for rotation
     * @return rotation
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * sets rotation to argument
     * @param other value to be set to rotation
     * @return this object
     */
    public Transform setRotation(Vector3f other) {
        this.rotation = other;
        return this;
    }

    /**
     * getter for scale
     * @return scale
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * sets scale to argument
     * @param other value to be set to scale
     * @return this object
     */
    public Transform setScale(Vector3f other) {
        this.scale = other;
        return this;
    }

    /**
     * getter for camera
     * @return camera
     */
    public static Camera getCamera() {
        return camera;
    }

    /**
     * sets camera to the argument
     * @param camera value to be set to camera
     */
    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }

    /**
     * generates matrix for adjustment of vertexes relative to width and height of window, fieldOfView and range of visibility
     * @param fieldOfView the entire area that a fixed camera can see
     * @param width width of window
     * @param height height of window
     * @param zNear minimal range in which object is visible
     * @param zFar max range in which object is visible
     * @return resulting matrix
     */
    public Matrix4f createProjectionMatrix(float fieldOfView, float width, float height, float zNear, float zFar) {
        float tanFOV = (float) Math.tan(Math.toRadians(fieldOfView / 2));
        float aspectRatio = width / height;
        float zRange = zNear - zFar;

        return new Matrix4f(
                new float[][]{{1 / (tanFOV * aspectRatio), 0, 0, 0,},
                        {0, 1 / tanFOV, 0, 0},
                        {0, 0, (-zNear - zFar) / zRange, 2 * zFar * zNear / zRange},
                        {0, 0, 1, 0}
                });
    }

    /**
     * generates matrix with for forward and up attributes of camera
     * @param forward forward vector of camera
     * @param up up vector of camera
     * @return resulting matrix
     */
    public Matrix4f createCameraMatrix(Vector3f forward, Vector3f up) {
        Vector3f f = new Vector3f(forward.getX(), forward.getY(), forward.getZ()).normalize();
        Vector3f r = new Vector3f(up.getX(), up.getY(), up.getZ()).normalize();
        r = r.cross(f);
        Vector3f u = f.cross(r);
        return new Matrix4f(
                new float[][]{{r.getX(), r.getY(), r.getZ(), 0},
                        {u.getX(), u.getY(), u.getZ(), 0},
                        {f.getX(), f.getY(), f.getZ(), 0},
                        {0, 0, 0, 1}
                }
        );
    }

    /**
     * setter for translation vector
     * @param X value to be set to x coordinate of translation vector
     * @param Y value to be set to y coordinate of translation vector
     * @param Z value to be set to z coordinate of translation vector
     * @return this object
     */
    public Transform setTranslation(float X, float Y, float Z) {
        translation.setX(X);
        translation.setY(Y);
        translation.setZ(Z);
        return this;
    }

    /**
     * multiplying translation, rotation and scale matrices combining every operation in one.
     * @return resulting matrix
     */
    public Matrix4f getTransformation() {
        Matrix4f translationMatrix = getTranslationMatrix(this.translation.getX(), this.translation.getY(), this.translation.getZ());
        Matrix4f rotationMatrix = getRotationMatrix(this.rotation.getX(), this.rotation.getY(), this.rotation.getZ());
        Matrix4f scaleMatrix = getScaleMatrix(this.scale.getX(), this.scale.getY(), this.scale.getZ());
        rotationMatrix.multiply(scaleMatrix);
        translationMatrix.multiply(rotationMatrix);
        return translationMatrix;
    }

    /**
     * multiplies existing transformation matrix to createProjectionMatrix() resulting in existing transformation
     * adjusted for current fieldView, width , height, zNear, zFar
     * @return resulting matrix
     */
    public Matrix4f getProjectedTransformation() {
        Matrix4f transformationMatrix = getTransformation();
        Matrix4f projectionMatrix = createProjectionMatrix(fieldOfView, width, height, zNear, zFar);
        Matrix4f cameraRotation = createCameraMatrix(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslation = getTranslationMatrix(-camera.getPosition().getX(), -camera.getPosition().getY(), -camera.getPosition().getZ());

        return projectionMatrix.multiply(cameraRotation.multiply(cameraTranslation.multiply(transformationMatrix)));
    }

    /**
     * generates matrix responsible for creating translated version of figure this matrix will be multiple with
     * @param x value responsible for direction and length of translation on x-axis
     * @param y value responsible for direction and length of translation on y-axis
     * @param z value responsible for direction and length of translation on z-axis
     * @return resulting matrix
     */
    public static Matrix4f getTranslationMatrix(float x, float y, float z) {
        return new Matrix4f(
                new float[][]{{1, 0, 0, x}
                        , {0, 1, 0, y}
                        , {0, 0, 1, z}
                        , {0, 0, 0, 1}});
    }

    /**
     * generates matrix responsible for creating rotated version of figure this matrix will be multiple with
     * @param x value responsible for degree of turn for x coordinate
     * @param y value responsible for degree of turn for z coordinate
     * @param z value responsible for degree of turn for y coordinate
     * @return
     */
    public static Matrix4f getRotationMatrix(float x, float y, float z) {
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);

        Matrix4f rz = new Matrix4f(
                new float[][]{
                        {(float) cos(z), (float) -sin(z), 0, 0}
                        , {(float) sin(z), (float) cos(z), 0, 0}
                        , {0, 0, 1, 0}
                        , {0, 0, 0, 1}});

        Matrix4f rx = new Matrix4f(
                new float[][]{
                        {1, 0, 0, 0}
                        , {0, (float) cos(x), (float) -sin(x), 0}
                        , {0, (float) sin(x), (float) cos(x), 0}
                        , {0, 0, 0, 1}});


        Matrix4f ry =
                new Matrix4f(
                        new float[][]{
                                {(float) cos(y), (float) -sin(y), 0, 0}
                                , {0, 1, 0, 0}
                                , {(float) sin(y), (float) cos(y), 1, 0}
                                , {0, 0, 0, 1}});

        ry.multiply(rx);
        rz.multiply(ry);
        return rz;

    }

    /**
     *
     * @param x value responsible for moving x coordinate during scale
     * @param y value responsible for moving y coordinate during scale
     * @param z value responsible for moving z coordinate during scale
     * @return resulting matrix
     */
    public static Matrix4f getScaleMatrix(float x, float y, float z) {
        return new Matrix4f(
                new float[][]{
                        {x, 0, 0, 0},
                        {0, y, 0, 0},
                        {0, 0, z, 0},
                        {0, 0, 0, 1}});
    }

}