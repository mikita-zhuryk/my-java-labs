public class Operation {

    private boolean left;
    private String symbol;
    private boolean right;

    public Operation(String s) {
        symbol = s;
    }

    public void setValues(boolean left, boolean right) {
        this.left = left;
        this.right = right;
    }

    public boolean calc() {
        boolean result = false;
        switch (symbol) {
            case "and": {
                result = left && right;
                break;
            }
            case "or": {
                result = left || right;
                break;
            }
            case "not": {
                result = !right;
                break;
            }
            default: {
                System.err.println("Wrong operation in input\n");
            }
        }
        return result;
    }



}
