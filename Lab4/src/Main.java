import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            CodeAnalyzer ca = new CodeAnalyzer();
            ca.read();
            ca.removeComments();
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("Text file not found or is corrupt");
        }
    }

}
