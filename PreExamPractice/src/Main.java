import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Reader r = new Reader();
        try {
            List<String> operations = r.readOperations("PreExamPractice\\input.txt");
            Map<String, Boolean> params = r.readParams("PreExamPractice\\input2.txt");
            List<OperationTree> trees = new ArrayList<>();
            operations.forEach(op -> trees.add(new OperationTree(params, op)));
            trees.forEach(tree -> {
                System.out.println(tree.toString() + " = " + tree.calcExpr());
            });
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

}
