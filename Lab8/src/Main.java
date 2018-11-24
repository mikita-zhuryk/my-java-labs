import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int size = sc.nextInt();
            Array a = new Array(size);
            a.fillRandom();
            a.print();
            a.sort();
            a.print();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
