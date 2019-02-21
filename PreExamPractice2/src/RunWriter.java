import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RunWriter {

    public void writeList(List<String> list, String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(path)) {
            list.forEach(pw::println);
        }
    }

}
