import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Main mainObj = new Main();
            CosineSeries cosine = new CosineSeries(mainObj.inputX(), mainObj.inputAccuracy());
            double myCosineSum = cosine.calculateSum();
            double builtInCosineSum = cosine.builtInCosine();
            mainObj.printResults(myCosineSum, builtInCosineSum);
        }
        catch(InputMismatchException e) {
            System.out.println("Wrong input, numbers please");
        }
        catch(NumberFormatException e) {
            System.out.println(e.toString());
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    private double inputX() {
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }

    private int inputAccuracy() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private void printResults(double myCosineSum, double builtInCosineSum) {
        System.out.println(String.format("My cosine: %1.3g", myCosineSum));
        System.out.println(String.format("Built-in cosine: %1.3g", builtInCosineSum));
    }

}
