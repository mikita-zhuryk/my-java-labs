import java.io.*;
import java.util.Date;

public class Logger {

    private BufferedWriter logWriter;
    private Date startupDate;

    public Logger(String logPath) throws IOException {
        logWriter = new BufferedWriter(new FileWriter(logPath, true));
        startupDate = new Date();
    }

    public void logRequest(String request, int respLength) throws IOException {
        String logMsg = String.format("STARTUP: [%1s] REQUEST: %2s RESPONSE LENGTH: %3d\n", startupDate.toString(),
                request, respLength);
        logWriter.write(logMsg);
    }

    public void closeLogFile() throws IOException {
        logWriter.close();
    }

}
