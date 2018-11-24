import java.util.Arrays;

public class Sort implements Runnable {

    private Integer[] arr;
    private IntComparator cmp;

    public Sort(Integer[] arr, int sortMethod) {
        this.arr = arr;
        cmp = new IntComparator(sortMethod);
    }

    @Override
    public void run() {
        Arrays.sort(arr, cmp);
    }

}
