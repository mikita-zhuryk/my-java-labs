import java.util.Scanner;

public class TextEditor {

    private String text;

    public TextEditor() {
        text = new String();
    }

    public void readText() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your text");
        String temp = new String(sc.nextLine());
        while (!temp.contentEquals("")) {
            text += temp;
            temp = sc.nextLine();
        }
    }

    public String removeNumbersFromTextCopy() {
        String[] words = text.split("[0-9]+[ ,.]");
        StringBuilder result = new StringBuilder();
        for(String word: words) {
            result.append(word);
        }
        return result.toString();
    }

}
