import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            InputMenu menu = new InputMenu(sc);
            Matrix matrix = menu.askMethodAndFillMatrix();
            if (matrix != null) {
                System.out.print("Global maximum among sorted rows equals ");
                System.out.print(matrix.findMaxValueInSortedRows());
            }
        }
        catch(NonPositiveIntegerInput e) {
            System.out.println(e.toString());
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

}