import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SQLParser {

    private List<String> requests;
    private ListIterator<String> iter;

    public SQLParser() {
        requests = new ArrayList<>();
        iter = requests.listIterator();
    }

    public void readRequests(String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNextLine()) {
                requests.add(sc.nextLine());
            }
        }
        iter = requests.listIterator();
    }

    public boolean hasMoreRequests() {
        return iter.hasNext();
    }

    public KeyedRequest parseRequest() {
        List<String> keys = new ArrayList<>();
        if (iter.hasNext()) {
            String request = iter.next();
            String[] requestFields = request.split("(\\s?SELECT\\s+)|(\\s+FROM\\s+)|(\\s+WHERE\\s+)");
            String rawKeys = requestFields[1];
            if (rawKeys.contains("*")) {
                keys.add("*");
            }
            else {
                StringTokenizer st = new StringTokenizer(rawKeys, ", ");
                while (st.hasMoreTokens()) {
                    keys.add(st.nextToken());
                }
            }
            String where = processWHERE(requestFields[3]);
            return new KeyedRequest(where, keys);
        }
        else {
            return null;
        }
    }

    private String processWHERE(String where) {
        StringBuilder fields = new StringBuilder();
        StringTokenizer st = new StringTokenizer(where, "= ;");
        String token = st.nextToken();
        fields.append(token);
        fields.append(", ");
        token = st.nextToken();
        if (token.equalsIgnoreCase("BETWEEN")) {
            fields.append(st.nextToken());
            fields.append(", ");
            st.nextToken();
            fields.append(st.nextToken());
        }
        else {
            fields.append(token);
        }
        return fields.toString();
    }

}
