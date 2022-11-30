package math;

/**
 * Math class Vector3f.java for simple mathematical operation on 3-dimensional vector
 */
public class Vector3f {

    private float x, y, z;

    /**
     * initializes three-dimensional vector at origin
     */
    public Vector3f(){
        this.x = 0F;
        this.y = 0F;
        this.z = 0F;
    }

    /**
     * initializes three-dimensional vector at given coordinates
     * @param x1 value of x-axis
     * @param y1 value of y-axis
     * @param z1 value of z-axis
     */
    public Vector3f(float x1, float y1, float z1) {
        this.x = x1;
        this.y = y1;
        this.z = z1;
    }

    /**
     * Returns length of this object using vector norm theorem
     * @return length/magnitude of this vector
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Returns dot product of this and argument vectors
     * @param other other three-dimensional vector
     * @return dot product
     */
    public float dot(Vector3f other) {
        return other.x * this.x + other.y * this.y + other.z * this.z;
    }

    /**
     * Calculate cross product of this and argument vector
     * @param other vector to cross product this with
     * @return this object
     */
    public Vector3f cross(Vector3f other) {
        this.x = this.y * other.z - this.z * other.y;
        this.y = this.z * other.x - this.x * other.z;
        this.y = this.x * other.y - this.y * other.x;
        return this;
    }

    /**
     * Normalizes this vector. Divides all the point by length of vector
     * @return this object
     * @throws IllegalArgumentException if length of this vector is zero
     */
    public Vector3f normalize() {
        float l = this.length();
        if (l == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        this.x = this.x / l;
        this.y = this.y / l;
        this.z = this.z / l;
        return this;
    }

    /**
     * Rotates this vector by angle in degrees.
     * @param angle angle in which vector will be rotated
     * @return this object
     */
    public Vector3f rotate(float angle) {
        float cos = (float) Math.cos(angle), sin = (float) Math.sin(angle);
        float x1 = (cos * this.x - sin * this.y), y1 = (sin * x + cos * this.y), z1 = this.z;
        x1 = x1 * cos + z1 * sin;
        z1 = z1 * cos - sin * x1;
        y1 = y1 * cos - z1 * sin;
        z1 = y1 * sin + z1 * cos;
        this.x = x1;
        this.y = z1;
        this.z = z1;
        return this;
    }


    /**
     * Rotates this vector by angle in degrees about some axis which is also a vector.
     * @param angle angle in which vector will be rotated
     * @param axis axis to rotate this vector about
     * @return this object
     */
    public Vector3f rotate(float angle, Vector3f axis) {
        float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getY() * sinHalfAngle;
        float rW = cosHalfAngle;

        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion rotationCopy = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotationCopy.conjugate();

        Quaternion result = rotation.mult(this).mult(conjugate);
        set(result.getA(), result.getB(), result.getC());
        return this;
    }

    /**
     * Adds argument vector to this vector. Increment every coordinate with argument vectors respective coordinate.
     * @param other vector value to increment this by
     * @return this object
     */
    public Vector3f add(Vector3f other) {
        this.x = this.x + other.x;
        this.y = this.y + other.y;
        this.z = this.z + other.z;
        return this;
    }

    /**
     * Subtracts argument vector to this vector. Decrement every coordinate with argument vectors respective coordinate.
     * @param other vector value to decrement this by
     * @return this object
     */
    public Vector3f sub(Vector3f other) {
        this.x = this.x - other.x;
        this.y = this.y - other.y;
        this.z = this.z - other.z;
        return this;
    }

    /**
     * Multiples this vector by argument Vector. Multiples every coordinate of this vector by argument vectors respective coordinate.
     * @param other vector value to multiply this by
     * @return this object
     */
    public Vector3f mult(Vector3f other) {
        this.x = this.x * other.x;
        this.y = this.y * other.y;
        this.z = this.z * other.z;
        return this;
    }

    /**
     * Divides this vector by argument Vector. Divides every coordinate of this vector by argument vectors respective coordinate.
     * @param other vector value to divide this by
     * @return this object
     * @throws IllegalArgumentException  if divisor has zero parameter
     */
    public Vector3f div(Vector3f other) {
        if (other.x != 0 && other.y != 0 && other.z != 0) {
            this.x = this.x / other.x;
            this.y = this.y / other.y;
            this.z = this.z / other.z;
            return this;
        }
        throw new IllegalArgumentException("Argument 'divisor' is 0");
    }

    /**
     * sets x parameter for this object
     * @param x1 value to be set
     * @return this object
     */
    public Vector3f setX(float x1){
        this.x = x1;
        return this;
    }

    /**
     * sets y parameter for this object
     * @param y1 value to be set
     * @return this object
     */
    public Vector3f setY(float y1){
        this.y = y1;
        return this;
    }

    /**
     * sets z parameter for this object
     * @param z1 value to be set
     * @return this object
     */
    public Vector3f setZ(float z1){
        this.z = z1;
        return this;
    }

    /**
     * sets all three parameters ot this object
     * @param x1 value to be set to x
     * @param y1 value to be set to y
     * @param z1 value to be set to z
     * @return this object
     */
    public Vector3f set(float x1, float y1, float z1){
        this.x = x1;
        this.y = y1;
        this.z = z1;
        return this;
    }

    /**
     * @return x of this object
     */
    public float getX(){
        return this.x;
    }

    /**
     * @return y of this object
     */
    public float getY(){
        return this.y;
    }

    /**
     * @return z of this object
     */
    public float getZ(){
        return this.z;
    }


}
