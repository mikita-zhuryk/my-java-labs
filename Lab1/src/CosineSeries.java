public class CosineSeries {

    private double x;
    private int accuracy;

    public CosineSeries(double x, int accuracy) {
        this.x = x;
        this.accuracy = accuracy;
    }

    public double calculateSum() {
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

    public double builtInCosine() {
        return Math.cos(x);
    }

}
