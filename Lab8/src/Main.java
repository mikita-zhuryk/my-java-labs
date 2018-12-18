import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter array size");
            int size = sc.nextInt();
            Array a = new Array(size);
            System.out.println("Enter min and max random values");
            int min = sc.nextInt();
            int max = sc.nextInt();
            a.fillRandom(min, max);
            a.print();
            a.sort();
            a.print();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
