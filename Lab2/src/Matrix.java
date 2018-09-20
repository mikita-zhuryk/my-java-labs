class Matrix {

    private double[][] value;
    private int height;
    private int width;

    public Matrix(double[][] value, int height, int width) throws NonPositiveIntegerInput {
        this.value = value;
        setHeight(height);
        setWidth(width);
    }

    private void setHeight(int height) throws NonPositiveIntegerInput {
        if (height <= 0) {
            throw new NonPositiveIntegerInput("Non-positive matrix height");
        }
        this.height = height;
    }

    private void setWidth(int width) throws NonPositiveIntegerInput {
        this.width = width;
    }

    public double findMaxValueInSortedRows() {
        double rowMax = 0;
        double allRowsMax = 0;
        for (int i = 0; i < height; ++i) {
            if (isAllValuesAscending(value[i])) {
                rowMax = value[i][width - 1];
            }
            else if (isAllValuesDescending(value[i])) {
                rowMax = value[i][0];
            }
            if (rowMax > allRowsMax) {
                allRowsMax = rowMax;
            }
        }
        return allRowsMax;
    }

    private boolean isAllValuesAscending(double[] row) {
        boolean isAscending = true;
        for (int i = 1; i < width; ++i) {
            if (row[i] < row[i - 1]) {
                isAscending = false;
                break;
            }
        }
        return isAscending;
    }

    private boolean isAllValuesDescending(double[] row) {
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
