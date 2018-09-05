import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter matrix dimensions");
        final int matrixWidth = sc.nextInt();
        final int matrixHeight = sc.nextInt();
        double [][] matrix = Main.readMatrix(sc, matrixHeight, matrixWidth);
        MatrixParser parser = new MatrixParser();
        System.out.print("Global maximum among sorted rows equals ");
        System.out.print(parser.findMaximumOfAllSortedRows(matrix, matrixHeight, matrixWidth));
    }

    private static double[][] readMatrix(Scanner scanner, int height, int width) {
        double[][] matrix = new double[height][width];
        System.out.println("Enter matrix elements");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

}