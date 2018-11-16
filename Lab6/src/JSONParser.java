import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONParser {

    public JSONParser() {}

    public String toJSON(List<Map<String, Object>> data) {
        StringBuilder sb = new StringBuilder();
        int objNum = data.size();
        if (objNum > 1) {
            sb.append('[');
        }
        Map<String, Object> company;
        Integer lastComma;
        for (int i = 0; i < objNum; ++i) {
            sb.append("{\r\n");
            company = data.get(i);
            for (Map.Entry<String, Object> compParam: company.entrySet()) {
                appendMapEntry(sb, compParam, 1);
            }
            lastComma = sb.lastIndexOf(",");
            sb.replace(lastComma, lastComma + 1, "");
            sb.append('}');
            if (i != objNum - 1) {
                if (objNum > 1) {
                    sb.append(", ");
                }
            }
        }
        if (objNum > 1) {
            sb.append(']');
        }
        return sb.toString();
    }

    private <V> void appendMapEntry(StringBuilder sb, Map.Entry<String, V> entry, Integer level) {
        String key = entry.getKey();
        V value = entry.getValue();
        if (value instanceof String) {
            //in case when a key has one value
            if (!value.equals("")) {
                appendMapKey(sb, key, level);
                tryToParseAndAppend(sb, (String)value);
                sb.append(",\r\n");
            }
        }
        else if (value instanceof Map) {
            //implement recursive call when map is Map<String, Map> (multiple fields for one key)
            appendMapKey(sb, key, level);
            sb.append("{\r\n");
            for (Map.Entry<String, Object> e: ((Map<String, Object>)value).entrySet()) {
                appendMapEntry(sb, e, level + 1);
            }
            sb.append("},");
        }
        else if (value instanceof Set) {
            //in case when a key has multiple values
            Integer lastComma;
            appendMapKey(sb, key, level);
            sb.append("[");
            for (String e: (Set<String>)value) {
                tryToParseAndAppend(sb, e);
                sb.append(", ");
            }
            lastComma = sb.lastIndexOf(", ");
            sb.replace(lastComma, lastComma + 2, "");
            sb.append("],\r\n");
        }
    }

    private void appendMapKey(StringBuilder sb, String key, Integer level) {
        for (int i = 0; i < level; ++i) {
            sb.append('\t');
        }
        sb.append(String.format("\"%1s\": ", key));
    }

    private void tryToParseAndAppend(StringBuilder sb, String value) {
        try {
            sb.append(Integer.parseInt((String)value));
        } catch (NumberFormatException nfe) {
            try {
                sb.append(Double.parseDouble((String)value));
            } catch (NumberFormatException nfe2) {
                sb.append(String.format("\"%1s\"", (String)value));
            }
        }
    }
}
