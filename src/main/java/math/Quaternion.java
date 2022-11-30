package math;

/**
 * Quaternion class Quaternion.java for rotation in three dimension and operations on them
 */
public class Quaternion {

    private float a;
    private float b;
    private float c;
    private float d;

    private float length = -1;

    /**
     * Constructor of quaternions
     * @param a first value of quaternion
     * @param b second value of quaternion
     * @param c third value of quaternion
     * @param d fourth value of quaternion
     */
    public Quaternion(float a, float b, float c, float d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Computes length of vector by square root from sum of coordinates squares
     * @return the length of this vector
     */
    public float getLength() {
        if (length < 0) {
            length = (float) Math.sqrt(a * a + b * b + c * c + d * d);
        }
        return length;
    }

    /**
     * Computes normalized quaternions by all coordinates divided by length
     * @return normalized quaternion
     */
    public Quaternion normalize() {
        a = a / getLength();
        b = b / getLength();
        c = c / getLength();
        d = d / getLength();
        length = -1;
        return this;
    }

    /**
     * This function conjugates quaternions by giving coordinates negative values
     * @return conjugated quaternion
     */
    public Quaternion conjugate() {
        return new Quaternion(-this.a, -this.b, -this.c, this.d);
    }

    /**
     * This function multiplies this quaternion by other quaternion
     * @param r quaternion to multiply this quaternion
     * @return this object
     */
    public Quaternion mult(Quaternion r) {
        float d_ = d * r.getD() - a * r.getA() - b * r.getB() - c * r.getC();
        float a_ = a * r.getD() + d * r.getA() + b * r.getC() - c * r.getB();
        float b_ = b * r.getD() + d * r.getB() + c * r.getA() - a * r.getC();
        float c_ = c * r.getD() + d * r.getC() + a * r.getB() - b * r.getA();
        return new Quaternion(a_, b_, c_, d_);
    }

    /**
     * This function multiplies this quaternion by vector
     * @param r vector to multiply this vector
     * @return this object
     */
    public Quaternion mult(Vector3f r) {
        float d_ = -a * r.getX() - b * r.getY() - c * r.getZ();
        float a_ =  d * r.getX() + b * r.getZ() - c * r.getY();
        float b_ =  d * r.getY() + c * r.getX() - a * r.getZ();
        float c_ =  d * r.getZ() + a * r.getY() - b * r.getX();
        return new Quaternion(a_, b_, c_, d_);
    }

    /**
     * Getter for first vector value of quaternion
     * @return first vector value of quaternion
     */
    public float getA() {
        return a;
    }

    /**
     * Setter for first vector value of quaternion
     * @param a second vector value to be set
     * @return this object
     */
    public Quaternion setA(float a) {
        this.a = a;
        return this;
    }

    /**
     * Getter for second vector value of quaternion
     * @return second vector value of quaternion
     */
    public float getB() {
        return b;
    }

    /**
     * Setter for second vector value of quaternion
     * @param b second vector value to be set
     * @return this object
     */
    public Quaternion setB(float b) {
        this.b = b;
        return this;
    }

    /**
     * Getter for third vector value of quaternion
     * @return third vector value of quaternion
     */
    public float getC() {
        return c;
    }

    /**
     * Setter for third vector value of quaternion
     * @param c third vector value to be set
     * @return this object
     */
    public Quaternion setC(float c) {
        this.c = c;
        return this;
    }

    /**
     * Getter for fourth vector value of quaternion
     * @return fourth vector value of quaternion
     */
    public float getD() {
        return d;
    }

    /**
     * Setter for fourth vector value of quaternion
     * @param d fourth vector value to be set
     * @return this object
     */
    public Quaternion setD(float d) {
        this.d = d;
        return this;
    }
}
