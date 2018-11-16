import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVParser {

    private List<String> keys;
    private final int nKeys;

    public CSVParser() {
        nKeys = 12;
        keys = new ArrayList<>();
    }

    public List<Company> readData(String inPath) throws FileNotFoundException {
        try(Scanner sc = new Scanner(new File(inPath))) {
            List<Company> companyData = new ArrayList<>();
            String keysStr = sc.nextLine();
            Scanner scKeys = new Scanner(keysStr);
            scKeys.useDelimiter(";");
            for (int i = 0; i < nKeys; ++i) {
                keys.add(scKeys.next());
            }
            Companies.setKeys(keys);
            int i = 0;
            while(sc.hasNextLine()) {
                i = 0;
                Map<String, Object> company = new HashMap<>();
                for (String word: sc.nextLine().split(";")) {
                    company.put(keys.get(i), word);
                    i++;
                }
                companyData.add(new Company(company));
            }
            return companyData;
        }
    }

}
