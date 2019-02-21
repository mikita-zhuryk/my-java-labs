import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpTreeNode {

    private String rawOpS;
    private String opS;
    private Operation operation;
    private OpTreeNode left;
    private OpTreeNode right;
    private Boolean value;

    public OpTreeNode(String opString, Map<String, Boolean> params) {
        opS = opString.trim().toLowerCase();
        rawOpS = opString;
        formatString();
        int opIndex = -1;
        boolean foundNOT = false;
        if ((opIndex = tryFindOR()) != -1) {
            operation = new Operation("or");
        }
        else if ((opIndex = tryFindAND()) != -1) {
            operation = new Operation("and");
        }
        else if ((opIndex = tryFindNOT()) != -1) {
            operation = new Operation("not");
            foundNOT = true;
        }
        if (!assignValue(params)) {
            if (!foundNOT) {
                left = new OpTreeNode(opS.substring(0, opIndex), params);
            }
            right = new OpTreeNode(opS.substring(opIndex + 3), params);
        }
    }

    private void formatString() {
        String copy = opS;
        if (opS.charAt(0) == '(' && opS.charAt(opS.length() - 1) == ')') {
            opS = opS.substring(1, opS.length() - 1);
        }
        if (!checkParentheses(0)) {
            opS = copy;
        }
    }

    private int tryFindOR() {
        Pattern p = Pattern.compile("^((.+\\))?[^\\(\\)]*)(or)([^\\(\\)]*(\\(.+)?)$");
        Matcher m = p.matcher(opS);
        if (m.find()) {
            int result;
            result = m.start(3);
            if (checkParentheses(result)) {
                return result;
            }
        }
        return -1;
    }

    private int tryFindAND() {
        Pattern p = Pattern.compile("^((.+\\))?[^\\(\\)]*)(and)([^\\(\\)]*(\\(.+)?)$");
        Matcher m = p.matcher(opS);
        if (m.find()) {
            int result;
            result = m.start(3);
            if (checkParentheses(result)) {
                return result;
            }
        }
        return -1;
    }

    private int tryFindNOT() {
        Pattern p = Pattern.compile("^(not)([^\\(\\)]*(\\(.+)?)$");
        Matcher m = p.matcher(opS);
        if (m.find()) {
            if (checkParentheses(0)) {
                return m.start();
            }
        }
        return -1;
    }

    private boolean assignValue(Map<String, Boolean> params) {
        value = null;
        if (opS.trim().equalsIgnoreCase("true")) {
            value = true;
        }
        else if (opS.trim().equalsIgnoreCase("false")) {
            value = false;
        }
        else if (params.containsKey(opS.trim().toLowerCase())){
            value = params.get(opS.trim().toLowerCase());
        }
        else {
            return false;
        }
        return true;
    }

    private boolean checkParentheses(int index) {
        int count = 0;
        for (int i = 0; i < index; ++i) {
            if (opS.charAt(i) == '(') {
                count++;
            }
            else if (opS.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        if (count != 0) {
            return false;
        }
        for (int i = index; i < opS.length(); ++i) {
            if (opS.charAt(i) == '(') {
                count++;
            }
            else if (opS.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        if (count != 0) {
            return false;
        }
        return true;
    }

    public boolean calcOp() {
        if (value == null) {
            if (left != null) {
                operation.setValues(left.calcOp(), right.calcOp());
            }
            else {
                operation.setValues(false, right.calcOp());
            }
            value = operation.calc();
        }
        return value;
    }

    public String toString() {
        return rawOpS;
    }

}
