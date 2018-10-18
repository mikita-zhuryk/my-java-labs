import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {

    private String html;

    public HTMLParser() {
        html = new String();
    }

    public void readHTML(String inPath) throws FileNotFoundException {
        try(Scanner sc = new Scanner(new File(inPath))) {
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append('\n');
            }
            html = sb.toString();
        }
    }

    public void printTags(String outPath) throws FileNotFoundException {
        Pattern p = Pattern.compile("<[^<>]+>");
        Matcher m = p.matcher(html);
        List<String> tags = new ArrayList<>();
        String curTag;
        char second;
        while (m.find()) {
            curTag = m.group(0);
            second = curTag.charAt(1);
            if (second != '/') {
                tags.add(curTag);
            }
        }
        tags.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        try(PrintStream ps = new PrintStream(outPath)) {
            tags.stream()
                .forEach(tag -> {ps.println(tag);});
        }
    }

    public void findTokens(List<String> tokens, String outPath, String outPathNotFound) throws FileNotFoundException {
        Map<String, Integer> findMap = new HashMap<>();
        String withoutTagsLower = html.replaceAll("<[^<>]+>", "").toLowerCase();
        Scanner sc = new Scanner(withoutTagsLower);
        String line;
        int i = 0;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            for (String token: tokens) {
                if (line.contains(token.toLowerCase())) {
                    if (!findMap.containsKey(token)) {
                        findMap.put(token, i + 1);
                    }
//                    tokens.remove(token);
                }
            }
            ++i;
        }
        try(PrintWriter pw = new PrintWriter(outPath)) {
            for (Map.Entry<String, Integer> item : findMap.entrySet()) {
                pw.print(item.getKey() + " found in line #" + item.getValue() + '\n');
            }
        }
        try(PrintWriter pw = new PrintWriter(outPathNotFound)) {
            for (String token: tokens) {
                if (!findMap.containsKey(token)) {
                    pw.print(token + " not found.\n");
                }
            }
        }
    }

}
