import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Logger logger = new Logger("Lab6\\logfile.txt");
            CSVParser parser = new CSVParser();
            Companies companies = new Companies(parser.readData("Lab6\\data.csv"));
            JSONParser json = new JSONParser();
            XMLParser xml = new XMLParser();
            Menu menu = new Menu(companies, json, xml, logger);
            menu.show();
            logger.closeLogFile();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
