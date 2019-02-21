import java.util.Comparator;
import java.util.List;

public class RunnableSort implements Runnable {

    private List<String> toSort;

    public RunnableSort(List<String> list) {
        toSort = list;
    }

    @Override
    public void run() {
        toSort.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
    }
}
