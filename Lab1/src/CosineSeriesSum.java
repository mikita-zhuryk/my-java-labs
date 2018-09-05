public class CosineSeriesSum {

    public static double calculate(double x, int accuracy) {
        double difference = Math.pow(10, -accuracy);
        double seriesPartialSum = 0.0;
        double currentTerm = 1.0;
        int currentFactorialNumber = 0;
        while (Math.abs(currentTerm) >= difference) {
            seriesPartialSum += currentTerm;
            currentTerm *= -Math.pow(x, 2) / ((currentFactorialNumber + 1) * (currentFactorialNumber + 2));
            currentFactorialNumber += 2;
        }
        return seriesPartialSum;
    }

}
