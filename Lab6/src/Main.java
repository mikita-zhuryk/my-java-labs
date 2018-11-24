import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Logger logger = null;
        try {
            logger = new Logger("Lab6\\logfile.txt");
            CSVParser parser = new CSVParser();
            Companies companies = new Companies(parser.readData("Lab6\\data.csv"));
            JSONParser json = new JSONParser();
            XMLParser xml = new XMLParser();
            Menu menu = new Menu(companies, json, xml, logger);
            menu.show();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
            if (logger != null) {
                logger.log(fnfe.getStackTrace().toString());
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
            if (logger != null) {
                logger.log(ioe.getStackTrace().toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            if (logger != null) {
                logger.log(e.getStackTrace().toString());
            }
        }
        finally {
            logger.closeLogFile();
        }
    }

}
