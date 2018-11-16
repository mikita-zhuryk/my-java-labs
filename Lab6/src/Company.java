import java.time.LocalDate;
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
        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);
        LocalDate date = LocalDate.parse((String)data.get("dateFoundation"));
        return (date.isAfter(dateFrom) && date.isBefore(dateTo));
    }

    public boolean employeeNumberBetween(int from, int to) {
        int num = (int)data.get("employeeCount");
        return (num >= from) && (num <= to);
    }

}
