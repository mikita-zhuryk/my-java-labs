import java.util.Scanner;

class MatrixReader {

    private Scanner sc;

    public MatrixReader(Scanner sc){
        this.sc = sc;
    }

    public Matrix readCalledFromInputMenu() throws NumberFormatException, NonPositiveIntegerInput {
        int height = this.readHeight();
        int width = this.readWidth();

        double[][] value = new double[height][width];
        System.out.println("Enter matrix elements");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                value[i][j] = sc.nextDouble();
            }
        }

        Matrix matrix = new Matrix(value, height, width);
        return matrix;
    }

    public int readHeight() throws NonPositiveIntegerInput {
        System.out.println("Enter matrix dimensions.\nHeight:");
        int height = sc.nextInt();
        if (height <= 0) {
            throw new NonPositiveIntegerInput("Non-positive matrix width");
        }
        return height;
    }

    public int readWidth() throws NonPositiveIntegerInput {
        System.out.println("Width:");
        int width = sc.nextInt();
        if (width <= 0) {
            throw new NonPositiveIntegerInput("Non-positive matrix width");
        }
        return width;
    }

}
