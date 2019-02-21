import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RunReader rr = new RunReader();
        RunWriter rw = new RunWriter();
        try {
            List<String> runList = rr.read("PreExamPractice2\\windows.in");
            RunQueue rq = new RunQueue();
            rq.compile(runList);
            rw.writeList(rq.sortedPrograms(), "PreExamPractice2\\windows1.out");
            rw.writeList(rq.getClosedPrograms(), "PreExamPractice2\\windows2.out");
            rw.writeList(rq.sortedCycling(), "PreExamPractice2\\windows3.out");
            rw.writeList(rq.getOrderedPrograms(), "PreExamPractice2\\windows4.out");
        }
        catch (FileNotFoundException ioe) {
            System.err.println("Failed to read run sequence");
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

}
