import java.util.Random;
import java.util.Scanner;

class InputMenu {

    private Scanner sc;
    private MatrixReader mr;

    public InputMenu(Scanner sc) {
        this.sc = sc;
        this.mr = new MatrixReader(sc);
    }

    public Matrix askMethodAndFillMatrix() throws NumberFormatException, NonPositiveIntegerInput {
        System.out.println("Choose input method.\n1. Manual;\n2. Random.");
        int choice = sc.nextInt();
        Matrix matrix;
        switch(choice) {
            case 1: {
                matrix = this.readMatrix();
                break;
            }
            case 2: {
                matrix = this.fillRandomly();
                break;
            }
            default: {
                matrix = null;
            }
        }
        return matrix;
    }

    private Matrix readMatrix() throws NonPositiveIntegerInput {
        return mr.readCalledFromInputMenu();
    }

    private Matrix fillRandomly() throws NonPositiveIntegerInput {
        int height = mr.readHeight();
        int width = mr.readWidth();

        double[][] values = new double[height][width];
        Random r = new Random();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                values[i][j] = r.nextInt(31);
            }
        }
        return new Matrix(values, height, width);
    }

}