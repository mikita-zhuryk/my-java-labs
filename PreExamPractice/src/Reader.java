import java.io.File;
import java.io.IOException;
import java.util.*;

public class Reader {

    public List<String> readOperations(String path) throws IOException {
        List<String> operations = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                operations.add(sc.next());
            }
        }
        return operations;
    }

    public Map<String, Boolean> readParams(String path) throws IOException {
        Map<String, Boolean> params = new HashMap<>();
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter(";");
            String line;
            String[] decomp;
            while (sc.hasNext()) {
                line = sc.next().replaceAll("\r\n", "");
                decomp = line.split("\\s*=\\s*");
                switch (decomp[1].toLowerCase()) {
                    case "true": {
                        params.put(decomp[0].toLowerCase(), true);
                        break;
                    }
                    case "false": {
                        params.put(decomp[0].toLowerCase(), false);
                        break;
                    }
                }
            }
        }
        return params;
    }
}
