import java.util.List;

public class KeyedRequest {

    private List<String> keys;
    private String request;

    public KeyedRequest(String req, List<String> k) {
        request = req;
        keys = k;
    }

    public List<String> getKeys() {
        return keys;
    }

    public String getRequest() {
        return request;
    }

}
