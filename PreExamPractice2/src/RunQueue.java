import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RunQueue {

    private List<String> openPrograms;
    private List<String> closedPrograms;
    private List<String> cyclingOperators;
    private List<String> orderedPrograms;
    private Integer current;

    public RunQueue() {
        openPrograms = new ArrayList<>();
        closedPrograms = new ArrayList<>();
        cyclingOperators = new ArrayList<>();
        orderedPrograms = new ArrayList<>();
        current = 0;
    }

    public void compile(List<String> runList) {
        int cycleNumber = 0;
        String program;
        for (String str: runList) {
            if (str.matches("Alt(\\+Tab)+")) {
                cycleNumber = str.split("\\+").length - 1;
                current = (current + cycleNumber) % openPrograms.size();
                cyclingOperators.add(str);
                orderedPrograms.add(openPrograms.get(current));
            }
            else if (str.matches("Close")) {
                program = openPrograms.get(current);
                openPrograms.remove((int)current);
                closedPrograms.add(program);
            }
            else if (str.matches("Run\\s+[^\\p{Punct}]+")) {
                program = str.split("Run\\s+")[1];
                openPrograms.add(current, program);
                orderedPrograms.add(program);
            }
        }
    }

    public List<String> sortedPrograms() {
        return openPrograms.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
    }

    public synchronized List<String> sortedCycling() {
        List<String> temp = new ArrayList<>(cyclingOperators);
        RunnableSort rs = new RunnableSort(temp);
        Thread t = new Thread(rs);
        t.start();
        try {
            t.join();
        }
        catch (InterruptedException ie) {
            System.err.println("Sorting was interrupted");
        }
        return temp;
    }

    public List<String> getOpenPrograms() {
        return openPrograms;
    }
    
    public List<String> getClosedPrograms() {
        return closedPrograms;
    }

    public List<String> getCycling() {
        return cyclingOperators;
    }

    public List<String> getOrderedPrograms() {
        return orderedPrograms;
    }

}
