import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HTMLParser parser = new HTMLParser();
        try {
            Main m = new Main();
            parser.readHTML("Lab5\\input1.html");
            parser.printTags("Lab5\\output1.out");
            parser.findTokens(m.readTokens("Lab5\\input2.in"), "Lab5\\output2.out", "Lab5\\output3.out");
        }
        catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
    }

    private List<String> readTokens(String inPath) throws FileNotFoundException {
        List<String> tokens = new ArrayList<>();
        Scanner sc = new Scanner(new File(inPath));
        sc.useDelimiter("[\\r\\n\\s]*;[\\r\\n\\s]*");
        while(sc.hasNext()) {
            tokens.add(sc.next());
        }
        return tokens;
    }

}
