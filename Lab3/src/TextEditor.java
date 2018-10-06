import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<String> delimArray = buildDelimArray();
        String s = text.toString();
        StringTokenizer st = new StringTokenizer(s, " .,");
        String token;
        StringBuilder result = new StringBuilder();
        int delimCounter = 0;
        if (isDelimiter(s.charAt(0))) {
            result.append(delimArray.get(0));
            delimCounter = 1;
        }
        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if (!isNumber(token)) {
                result.append(token);
            }
            if (delimCounter < delimArray.size()) {
                result.append(delimArray.get(delimCounter));
                ++delimCounter;
            }
        }
        return result.toString();
    }

    private List<String> buildDelimArray() {
        List<String> delimArray = new ArrayList<String>();
        Pattern p = Pattern.compile("[. ,]+");
        Matcher m = p.matcher(text.toString());
        while (m.find()) {
            delimArray.add(m.group(0));
        }
        return delimArray;
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

    private boolean isDelimiter(char c) {
        if ((c == ' ') || (c == '.') || (c == ',')) {
            return true;
        }
        else {
            return false;
        }
    }

}
