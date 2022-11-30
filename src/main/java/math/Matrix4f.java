package math;

/**
 * Math class Matrix4f.java for simple mathematical operations on 4-dimensional matrix
 */
public class Matrix4f {

    /**
     * immutable variable for matrix length
     */
    public static final short MATRIX_LEN = 4;
    private float[][] matrix;

    /**
     * 4-dimensional matrix constructor
     * @param matrix value of matrix
     */
    public Matrix4f(float [][] matrix){
        this.matrix = matrix;
    }

    /**
     * @return value of matrix
     */
    public float[][] getMatrix(){
        return this.matrix;
    }

    /**
     * @return separate cell of the 4x4 matrix
     */
    public float getCellValue(int i, int j){
        return this.matrix[i][j];
    }

    /**
     * @param matrix value to be set
     * @return this object
     */
    public Matrix4f setMatrix(float[][] matrix){
        this.matrix = matrix;
        return this;
    }

    /**
     * @param entry value to be set on separate cell in 4x4 matrix
     * @return this object
     */
    public Matrix4f setSeparateCell(int i, int j, float entry){
        this.matrix[i][j] = entry;
        return this;
    }

    /**
     * Creates identity matrix
     * @return 4x4 identity matrix
     */
    public static Matrix4f identity(){
        float[][] idMat = new float[MATRIX_LEN][MATRIX_LEN];

        for(int i = 0; i < MATRIX_LEN; i++){
            for(int j = 0; j < MATRIX_LEN; j++){
                idMat[i][j] = (i == j) ? 1 : 0;
            }
        }
        return new Matrix4f(idMat);
    }

    /**
     * Multiplies this matrix by argument matrix
     * @param other matrix value to multiply this by
     * @return this object
     */
    public Matrix4f multiply(Matrix4f other){

        float[][] tempMat = new float[MATRIX_LEN][MATRIX_LEN];

        for(int i = 0; i< MATRIX_LEN; i++){
            for(int j = 0; j < MATRIX_LEN; j++){
                tempMat[i][j] = 0;
                for(int k = 0; k < MATRIX_LEN; k++){
                    tempMat[i][j] += getMatrix()[i][k] * other.getMatrix()[k][j];
                }
            }
        }
        setMatrix(tempMat);
        return this;
    }


}
