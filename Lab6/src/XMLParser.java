import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLParser {

    public XMLParser() {}

    public String toXML(List<Map<String, Object>> params) {
        StringBuilder sb = new StringBuilder("<companies>\r\n");
        int objNum = params.size();
        Map<String, Object> company;
        for (int i = 0; i < objNum; ++i) {
            sb.append("\t<company>\r\n");
            company = params.get(i);
            for (Map.Entry<String, Object> compParam: company.entrySet()) {
                appendMapEntry(sb, compParam, 2);
            }
            sb.append("\t</company>\r\n");
        }
        sb.append("</companies>\r\n");
        return sb.toString();
    }

    private <V> void appendMapEntry(StringBuilder sb, Map.Entry<String, V> entry, Integer level) {
        String key = entry.getKey();
        V value = entry.getValue();
        if (value instanceof String) {
            //in case when a key has one value
            if (!value.equals("")) {
                for (int i = 0; i < level; ++i) {
                    sb.append('\t');
                }
                sb.append(String.format("<%1s>%2s</%3s>\r\n", key, (String)value, key));
            }
        }
        else if (value instanceof Map) {
            //implement recursive call when map is Map<String, Map> (multiple fields for one key)
            sb.append(String.format("<%1s>\r\n", key));
            for (Map.Entry<String, Object> e: ((Map<String, Object>)value).entrySet()) {
                appendMapEntry(sb, e, level + 1);
            }
            sb.append(String.format("</%1s>\r\n", key));
        }
        else if (value instanceof Set) {
            //in case when a key has multiple values
            for (String e: (Set<String>)value) {
                for (int i = 0; i < level; ++i) {
                    sb.append('\t');
                }
                sb.append(String.format("<%1s>%2s</%3s>\r\n", key, e, key));
            }
        }
    }

}
