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
        StringBuilder request = new StringBuilder("");
        try {
            switch (choice) {
                case 1: {
                    request.append("shortTitle, ");
                    request.append(sc.next());
                    break;
                }
                case 2: {
                    request.append("branch, ");
                    request.append(sc.next());
                    break;
                }
                case 3: {
                    request.append("activity, ");
                    request.append(sc.next());
                    break;
                }
                case 4: {
                    request.append("dateFoundation, ");
                    request.append(sc.nextInt());
                    request.append(", ");
                    request.append(sc.nextInt());
                    break;
                }
                case 5: {
                    request.append("countEmployees, ");
                    request.append(sc.nextInt());
                    request.append(", ");
                    request.append(sc.nextInt());
                    break;
                }
            }
            process(request.toString());
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    private void process(String request) throws Exception {
        Companies response = companyData.process(request, logger);
        try(FileWriter jsonWriter = new FileWriter("Lab6\\json.txt");
            FileWriter xmlWriter = new FileWriter("Lab6\\xml.txt")) {
            jsonWriter.write(jsonParser.toJSON(response.rawData()));
            xmlWriter.write(xmlParser.toXML(response.rawData()));
        }
    }

}
