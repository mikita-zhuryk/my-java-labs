import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            double x = sc.nextDouble();
            int accuracy = sc.nextInt();
            double myCosineSum = CosineSeriesSum.calculate(x, accuracy);
            double builtInCosineSum = Math.cos(x);
            System.out.println("My cosine: " + myCosineSum);
            System.out.printf("Built-in cosine: " + builtInCosineSum);
        }
        catch(NumberFormatException e) {
            System.out.println(e.toString());
        }
    }

}
