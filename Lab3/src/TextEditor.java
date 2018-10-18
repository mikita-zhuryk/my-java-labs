import java.util.Scanner;
import java.util.StringTokenizer;

public class TextEditor {

    private StringBuilder text;

    public TextEditor() {
        text = new StringBuilder();
    }

    public void readText() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your text");
        String temp = sc.nextLine();
        while (!temp.contentEquals("")) {
            text.append(temp);
            temp = sc.nextLine();
        }
    }

    public String removeNumbersFromTextCopy() {
        String s = text.toString();
        StringTokenizer st = new StringTokenizer(s, " .,", true);
        String token;
        StringBuilder result = new StringBuilder();
        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if (!isNumber(token)) {
                result.append(token);
            }
        }
        return result.toString();
    }

    private boolean isNumber(String token) {
        char c;
        for (int i = 0; i < token.length(); ++i) {
            c = token.charAt(i);
            if (!Character.isDigit(c)) {
                if ((i != 0) || (c != '-')) {
                    return false;
                }
            }
        }
        return true;
    }

}
