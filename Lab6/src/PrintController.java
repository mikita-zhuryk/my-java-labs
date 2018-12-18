import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintController {

    private static Companies filterKeys(Companies comp, List<String> keys) {
        if (!keys.contains("*") && !(keys.size() == 0)) {
            Companies result = new Companies();
            comp
                    .rawData()
                    .forEach(company -> {
                        Map<String, Object> data = new HashMap<>();
                        Object temp;
                        for (String key : keys) {
                            temp = company.get(key);
                            data.put(key, temp);
                        }
                        result.add(new Company(data));
                    });
            return result;
        }
        else {
            return comp;
        }
    }

    public static void print(String filename, Companies comp, List<String> keys) throws IOException {
        try(FileWriter jsonWriter = new FileWriter("Lab6\\" + filename + ".json");
            FileWriter xmlWriter = new FileWriter("Lab6\\" + filename + ".xml")) {
            Companies filtered = filterKeys(comp, keys);
            JSONParser jsonParser = new JSONParser();
            XMLParser xmlParser = new XMLParser();
            jsonWriter.write(jsonParser.toJSON(filtered.rawData()));
            xmlWriter.write(xmlParser.toXML(filtered.rawData()));
        }
    }

}
