import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Companies companyData;
    private JSONParser jsonParser;
    private XMLParser xmlParser;
    private Logger logger;

    public Menu(Companies companies, JSONParser jsonP, XMLParser xmlP, Logger l) {
        companyData = companies;
        jsonParser = jsonP;
        xmlParser = xmlP;
        logger = l;
    }

    public void show() throws Exception {
        System.out.println("Choose what you want me to do:");
        System.out.println("1. Find a company via short name;");
        System.out.println("2. Find a company via branch;");
        System.out.println("3. Find a company via activity type;");
        System.out.println("4. Find a company via foundation date interval;");
        System.out.println("5. Find a company via employee count.");
        act();
    }

    private void act() throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        try {
            String request = buildRequest(choice, sc);
            processRequest(request);
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
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

    private void processRequest(String request) throws Exception {
        Companies response = companyData.process(request, logger);
        try(FileWriter jsonWriter = new FileWriter("Lab6\\json.txt");
            FileWriter xmlWriter = new FileWriter("Lab6\\xml.txt")) {
            jsonWriter.write(jsonParser.toJSON(response.rawData()));
            xmlWriter.write(xmlParser.toXML(response.rawData()));
        }
    }

}
