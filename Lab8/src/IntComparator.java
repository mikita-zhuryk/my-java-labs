import java.util.Comparator;

public class IntComparator implements Comparator<Integer> {

    private final int sortMethod;

    public IntComparator() {
        this(1);
    }

    public IntComparator(int sortMethod) {
        this.sortMethod = sortMethod;
    }

    @Override
    public int compare(Integer a, Integer b) {
        int result;
        switch (sortMethod) {
            case 1: {
                result = a - b;
                break;
            }
            case 2: {
                result = b - a;
                break;
            }
            case 3: {
                result = countDigits(a) - countDigits(b);
                break;
            }
            case 4: {
                result = countDigits(b) - countDigits(a);
                break;
            }
            default: {
                result = 0;
            }
        }
        return result;
    }

    private int countDigits(Integer a) {
        int count = 0;
        while (a != 0) {
            a /= 10;
            count++;
        }
        return count;
    }

}
