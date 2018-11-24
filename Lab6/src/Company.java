import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Company {

    private Map<String, Object> data;

    public Company(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> rawData() {
        return data;
    }

    public boolean shortTitleEquals(String sh) {
        return data.get("shortTitle").equals(sh);
    }

    public boolean branchEquals(String branch) {
        return data.get("branch").equals(branch);
    }

    public boolean activityEquals(String activity) {
        return data.get("activity").equals(activity);
    }

    public boolean dateBetween(String from, String to) {
        LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
        LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ISO_DATE);
        String dateS = (String)data.get("dateFoundation");
        LocalDate date = LocalDate.parse(dateS);
        return (date.isAfter(dateFrom) && date.isBefore(dateTo)
                || date.isEqual(dateFrom) || date.isEqual(dateTo));
    }

    public boolean employeeNumberBetween(int from, int to) {
        int num = Integer.parseInt((String)data.get("countEmployees"));
        return (num >= from) && (num <= to);
    }

}
