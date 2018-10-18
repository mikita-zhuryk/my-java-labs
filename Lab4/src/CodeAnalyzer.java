import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CodeAnalyzer {

    StringBuilder code;

    public CodeAnalyzer() {
        code = new StringBuilder();
    }

    public void read() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Lab4\\src\\Code.txt"));
        while (sc.hasNextLine()) {
            code.append(sc.nextLine());
            code.append("\n");
        }
    }

    public void removeComments() throws FileNotFoundException {
        boolean insideMultiline = false;
        int commentBegin = 0;
        boolean insideQuote = false;
        int backslashNumber = 0;
        for (int i = 1; i < code.length() - 1; ++i) {
            if (!insideMultiline) {
                if ((code.charAt(i) == '"')) {
                    backslashNumber = 0;
                    while (code.charAt(i - backslashNumber - 1) == '\\') {
                        backslashNumber++;
                    }
                    if ((backslashNumber % 2 == 0) && (code.charAt(i - 1) != '\'') && (code.charAt(i + 1) != '\'')) {
                        insideQuote = !insideQuote;
                    }
                }
            }
            if (!insideQuote) {
                if (code.charAt(i) == '*') {
                    if (code.charAt(i - 1) == '/') {
                        insideMultiline = true;
                        commentBegin = i - 1;
                    }
                    if (insideMultiline && (code.charAt(i + 1) == '/')) {
                        insideMultiline = false;
                        code.replace(commentBegin, i + 2, "");
                        i = i - (i + 1 - commentBegin);
                    }
                }
                else if ((code.charAt(i) == '/') && (code.charAt(i + 1) == '/')) {
                    int end = code.toString().indexOf('\n', i);
                    code.replace(i, end, "");
                }
            }
        }
        try(PrintStream ps = new PrintStream("Lab4\\src\\output.txt")) {
            ps.print(code.toString());
        }
    }


}
