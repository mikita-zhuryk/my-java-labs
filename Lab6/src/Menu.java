import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Companies companyData;
    private Logger logger;

    private final int CHOSE_SQL = 6;

    public Menu(Companies companies, Logger l) {
        companyData = companies;
        logger = l;
    }

    public void show() throws Exception {
        System.out.println("Choose what you want me to do:");
        System.out.println("1. Find a company via short name;");
        System.out.println("2. Find a company via branch;");
        System.out.println("3. Find a company via activity type;");
        System.out.println("4. Find a company via foundation date interval;");
        System.out.println("5. Find a company via employee count.");
        System.out.println("6. Process SQL SELECT requests.");
        act();
    }

    private void act() throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        try {
            if (choice == CHOSE_SQL) {
                processSQL();
            }
            else {
                String request = buildRequest(choice, sc);
                List<String> keys = new ArrayList<>();
                PrintController.print("manual", processRequest(request), keys);
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    private void processSQL() throws Exception {
        SQLParser sqlp = new SQLParser();
        sqlp.readRequests("Lab6\\requests.txt");
        int i = 0;
        while (sqlp.hasMoreRequests()) {
            KeyedRequest kr = sqlp.parseRequest();
            Companies data = processRequest(kr.getRequest());
            PrintController.print("request" + (i + 1), data, kr.getKeys());
            ++i;
        }
    }

    private String buildRequest(int choice, Scanner sc) {
        StringBuilder request = new StringBuilder();
        switch (choice) {
            case 1: {
                request.append("shortTitle, ");
                System.out.println("Enter short title\r\n");
                request.append(sc.next());
                break;
            }
            case 2: {
                request.append("branch, ");
                System.out.println("Enter branch\r\n");
                request.append(sc.next());
                break;
            }
            case 3: {
                request.append("activity, ");
                System.out.println("Enter activity\r\n");
                request.append(sc.next());
                break;
            }
            case 4: {
                request.append("dateFoundation, ");
                System.out.println("Enter lower date border\r\n");
                request.append(sc.next());
                request.append(", ");
                System.out.println("Enter upper date border\r\n");
                request.append(sc.next());
                break;
            }
            case 5: {
                request.append("countEmployees, ");
                System.out.println("Enter lower employees # border\r\n");
                request.append(sc.nextInt());
                request.append(", ");
                System.out.println("Enter upper employees # border\r\n");
                request.append(sc.nextInt());
                break;
            }
        }
        return request.toString();
    }

    private Companies processRequest(String request) throws Exception {
        return companyData.process(request, logger);
    }

}
