public class MatrixParser {

    public double findMaximumOfAllSortedRows(double[][] matrix, int height, int width) {
        double currentConditionalMaximum = 0;
        double globalConditionalMaximum = 0;
        for (int i = 0; i < height; ++i) {
            if (checkRowAscendance(matrix[i], width)) {
                currentConditionalMaximum = matrix[i][width - 1];
            }
            if (checkRowDescendance(matrix[i], width)) {
                currentConditionalMaximum = matrix[i][0];
            }
            if (currentConditionalMaximum > globalConditionalMaximum) {
                globalConditionalMaximum = currentConditionalMaximum;
            }
        }
        return globalConditionalMaximum;
    }

    private boolean checkRowAscendance(double[] row, int width) {
        boolean isAscending = true;
        for (int i = 1; i < width; ++i) {
            if (row[i] < row[i-1]) {
                isAscending = false;
                break;
            }
        }
        return isAscending;
    }

    private boolean checkRowDescendance(double[] row, int width) {
        boolean isDescending = true;
        for (int i = 1; i < width; ++i) {
            if (row[i] > row[i-1]) {
                isDescending = false;
                break;
            }
        }
        return isDescending;
    }

}
