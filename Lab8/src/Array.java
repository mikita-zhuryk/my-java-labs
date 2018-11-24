import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Array {

    private Integer[] array;

    public Array(int size) {
        array = new Integer[size];
    }

    public void fillRandom() {
        Random r = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = r.nextInt(100);
        }
    }

    public synchronized void print() {
        System.out.println(array.length);
        Arrays.stream(array).forEach(num->{
            System.out.print(num + " ");
        });
    }

    public synchronized void sort() throws InterruptedException {
        printSortInfo();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        Sort sort = new Sort(array, choice);
        Thread s = new Thread(sort);
        s.start();
        s.join();
    }

    private void printSortInfo() {
        System.out.println("Choose sort method:\n");
        System.out.println("1. Increasing value\n");
        System.out.println("2. Decreasing value\n");
        System.out.println("3. Increasing number of digits\n");
        System.out.println("4. Decreasing number of digits\n");
    }

}
