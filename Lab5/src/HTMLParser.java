import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {

    private String html;

    public HTMLParser() {
        html = new String();
    }

    public void printTags(String outPath) {
        Pattern p = Pattern.compile("<.*>");
        Matcher m = p.matcher(html);
        try(PrintStream ps = new PrintStream(outPath)) {

        }
    }

}
