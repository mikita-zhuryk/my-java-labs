import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CodeAnalyzer {

    StringBuilder code;

    public CodeAnalyzer() {
        code = new StringBuilder();
    }

    public void read() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\admin\\Documents\\Java_Projects\\Lab4\\src\\Code.txt"));
        while (sc.hasNextLine()) {
            code.append(sc.nextLine());
            code.append("\n");
        }
    }

    public void removeComments() {
        String[] fragments = code.toString().split(";[ \\t]*//.*", Pattern.LITERAL);
        for (String str: fragments) {
            System.out.print(str);
        }
    }


}
