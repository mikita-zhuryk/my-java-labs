import java.util.Map;

public class OperationTree {

    private OpTreeNode root;

    public OperationTree(Map<String, Boolean> p, String expr) {
        expr = expr.replaceAll("\r\n", "");
        root = new OpTreeNode(expr, p);
    }

    public boolean calcExpr() {
        return root.calcOp();
    }

    public String toString() {
        return root.toString();
    }

}
